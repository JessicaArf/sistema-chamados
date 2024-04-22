package com.soulcode.sistemachamadosdois.repositories;

import com.soulcode.sistemachamadosdois.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByClientUserId(Long id);
}
