package uz.pdp.cinema_room.service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import net.glxn.qrgen.javase.QRCode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import uz.pdp.cinema_room.model.*;
import uz.pdp.cinema_room.projections.TicketProjection;
import uz.pdp.cinema_room.repository.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.*;

import static java.time.LocalTime.now;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @Autowired
    ReservedHallService reservedHallService;

    @Autowired
    SeatService seatService;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    PriceCategoryRepository priceCategoryRepository;

    @Autowired
    PurchaseWaitingTimeRepository purchaseWaitingTimeRepository;

    @Autowired
    AttachmentRepository attachmentRepo;

    @Autowired
    AttachmentContentRepository attachmentContentRepo;

    private final UUID serialNumber = UUID.randomUUID();


    public TicketProjection getTicket(UUID id) {
        return ticketRepository.getTicketById(id);
    }

    public UUID saveTicket(UUID user_id, UUID rh_id, UUID seat_id) {
        Cart cart = new Cart(null, userService.getUserById(user_id));
        cartRepository.save(cart);

        TicketStatus status = TicketStatus.getStatusByDisplayStatus("new");

        Double initPrice = movieRepository.getPriceByRHId(rh_id);

        Double priceSeat = priceCategoryRepository.getPrice(seat_id);

        Double hallFee = hallRepository.getFee(seat_id);

        double price;
        if (hallFee != null) {
            price = (hallFee * initPrice) / 100 + (initPrice * priceSeat) / 100 + initPrice;
        } else {
            price = (initPrice * priceSeat) / 100 + initPrice;
        }
        Ticket ticket = new Ticket();

        ticket.setReservedHall(reservedHallService.getReservedHallById(rh_id));

        ticket.setCart(cart);

        ticket.setSeat(seatService.getSeatById(seat_id));

        ticket.setStatus(status);

        ticket.setPrice(price);

        ticket.setSerialNumber(serialNumber);

        AttachmentContent fileAttachment = new AttachmentContent();
        Attachment attachment = new Attachment();

        try {
            byte[] bytes = generateQRCodeImage(String.valueOf(serialNumber));
            fileAttachment.setData(bytes);
            attachment.setSize(bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        attachment.setFileName("qrCode-" + now());
        attachment.setContentType("png");
        Attachment savedFile = attachmentRepo.save(attachment);

        fileAttachment.setAttachment(savedFile);

        attachmentContentRepo.save(fileAttachment);

        ticket.setQr_code(savedFile);
        ticketRepository.save(ticket);

        Integer waitingMinute = purchaseWaitingTimeRepository.getWaitingMinute();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Ticket ticketByIdForCart = ticketRepository.getTicketBYId(ticket.getId());
                try {
                    if (ticketByIdForCart.getStatus().equals(TicketStatus.NEW)) {
                        ticketRepository.deleteById(ticket.getId());
                        System.out.println("Ticket is deleted " + ticket.getId());
                    }
                } catch (NullPointerException ignored) {
                }
            }
        };
        Timer timer = new Timer();
        System.out.println("after " + waitingMinute + " minutes ticket will be deleted!");
        timer.schedule(timerTask, waitingMinute * 60000);
        return ticket.getId();
    }


    public void sendmail(UUID ticket_id, String pdfUrl) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ch.eldor1999@gmail.com", "choriyev1007");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ch.eldor1999@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("eldordeveloper1999@gmail.com"));
        msg.setSubject("Dear Client");
        msg.setContent("Cinema room email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Cinema room email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile(pdfUrl);
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

    public static byte[] generateQRCodeImage(String qrcodeText) throws Exception {
        ByteArrayOutputStream stream = QRCode
                .from(qrcodeText)
                .withSize(250, 250)
                .stream();
        return stream.toByteArray();
    }

    public String generatePdfTicket(UUID ticket_id) throws FileNotFoundException {
        final String imgDirectory = "./src/main/resources/static/";

        Ticket ticket = ticketRepository.getTicketBYId(ticket_id);

        String imgLocation = imgDirectory + "Ticket.pdf";
        PdfWriter writer = new PdfWriter(new FileOutputStream(imgLocation));
        PdfDocument pdfDocument = new PdfDocument(writer);

        pdfDocument.setDefaultPageSize(PageSize.A4);
        pdfDocument.addNewPage();


        Document document = new Document(pdfDocument);
        Paragraph paragraph = new Paragraph("Ticket").setTextAlignment(TextAlignment.CENTER).setFontSize(25);

        document.add(paragraph);


        float[] pointColumn = {60F, 120F, 120F, 120F, 120F};
        Table table = new Table(pointColumn);
        table.setTextAlignment(TextAlignment.CENTER).setHorizontalAlignment(com.itextpdf.layout.property.HorizontalAlignment.CENTER);
        table.addCell(new Cell().add("" + ticket.getId()));
        table.addCell(new Cell().add("" + ticket.getPrice()));
//        table.addCell(new Cell().add("" + ticketById.getSeat()));
        document.add(table);


        AttachmentContent file = attachmentContentRepo.getByAttachmentId(ticket.getQr_code().getId());

        ImageData imageData = ImageDataFactory.create(file.getData());

        Image img = new Image(imageData);

        // Creating Image object from the imagedata
        document.add(img);
        document.close();

        return imgLocation;
    }

    public void deleteTicket(UUID ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    public Ticket getTicketById(UUID ticket_id) {
        return ticketRepository.getTicketBYId(ticket_id);
    }
}
