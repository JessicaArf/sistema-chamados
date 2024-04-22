package com.soulcode.sistemachamadosdois.service;

import com.soulcode.sistemachamadosdois.model.Client;
import com.soulcode.sistemachamadosdois.model.Role;
import com.soulcode.sistemachamadosdois.model.Ticket;
import com.soulcode.sistemachamadosdois.repositories.RoleRepository;
import com.soulcode.sistemachamadosdois.repositories.TicketRepository;
import com.soulcode.sistemachamadosdois.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final UserRepository clientRepository;
    private final RoleRepository roleRepository;
    private final TicketRepository ticketRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Client createClient(Client client) throws Exception {
      Role role = roleRepository.findByName(Role.Values.CLIENT.name());
      Optional<Client> clientFromDb = clientRepository.findByEmail(client.getEmail());
        if (clientFromDb.isPresent()) {
            throw new Exception("Já existe um cliente cadastrado com esse email.");
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRole(role);
        client.setActive(true);
        return clientRepository.save(client);
    }

    public List<Ticket> getTicketById(Long clientId)  {
        List<Ticket> tickets = ticketRepository.findByClientUserId(clientId);
        return tickets;
    }

}
