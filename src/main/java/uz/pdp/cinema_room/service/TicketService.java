package uz.pdp.cinema_room.service;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room.model.*;
import uz.pdp.cinema_room.projections.TicketProjection;
import uz.pdp.cinema_room.repository.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

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


        Double price = (hallFee * initPrice)/100 + (initPrice * priceSeat)/100;

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

        return ticket.getId();
    }


    public static byte[] generateQRCodeImage(String qrcodeText) throws Exception {
        ByteArrayOutputStream stream = QRCode
                .from(qrcodeText)
                .withSize(250, 250)
                .stream();
//        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
        return stream.toByteArray();
    }


    public void updateTicket(UUID ticketId, Ticket ticketData) {
        ticketRepository.deleteById(ticketId);
        ticketRepository.save(ticketData);
    }


    @Scheduled()
    public void deleteTicket(UUID ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    public Document generatePdfTicket(UUID ticket_id) throws FileNotFoundException {

        TicketProjection ticketById = ticketRepository.getTicketById(ticket_id);

        PdfWriter writer = new PdfWriter(new FileOutputStream("Ticket.pdf"));
        PdfDocument pdfDocument = new PdfDocument(writer);

        pdfDocument.setDefaultPageSize(PageSize.A4);
        pdfDocument.addNewPage();


        Document document = new Document(pdfDocument);
        Paragraph paragraph = new Paragraph("Ticket").setTextAlignment(TextAlignment.CENTER).setFontSize(25);

        document.add(paragraph);


        float[] pointColumn = {60F, 120F, 120F, 120F, 120F};
        Table table = new Table(pointColumn);
        table.setTextAlignment(TextAlignment.CENTER).setHorizontalAlignment(com.itextpdf.layout.property.HorizontalAlignment.CENTER);
        table.addCell(new Cell().add("" + ticketById.getId()));
        table.addCell(new Cell().add("" + ticketById.getPrice()));
//        table.addCell(new Cell().add("" + ticketById.getSeat()));
        document.add(table);

//        AttachmentContent file = attachmentContentRepo.getByTicketId(ticket_id);
//        Attachment attachment = file.getAttachment();
//
//        document.add((IBlockElement) attachment);
        document.close();

        return document;
    }

    public Ticket getTicketById(UUID ticket_id) {
        return ticketRepository.getById(ticket_id);
    }
}
