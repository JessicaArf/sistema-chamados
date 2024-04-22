package com.soulcode.sistemachamadosdois.service;

import com.soulcode.sistemachamadosdois.model.Client;
import com.soulcode.sistemachamadosdois.model.Ticket;
import com.soulcode.sistemachamadosdois.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket, Client client){
        ticket.setClient(client);
        ticket.setStatus(Ticket.TicketStatus.OPEN);
        return ticketRepository.save(ticket);
    }

}
