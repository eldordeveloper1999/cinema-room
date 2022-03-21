package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room.model.Hall;
import uz.pdp.cinema_room.model.Ticket;
import uz.pdp.cinema_room.repository.TicketRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public Optional<Ticket> getTicket(UUID id) {
        return ticketRepository.findById(id);
    }

    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void updateTicket(UUID ticketId, Ticket ticketData) {
            ticketRepository.deleteById(ticketId);
            ticketRepository.save(ticketData);
            }


    @Scheduled()
    public void deleteTicket(UUID ticketId) {
        ticketRepository.deleteById(ticketId);
    }

}
