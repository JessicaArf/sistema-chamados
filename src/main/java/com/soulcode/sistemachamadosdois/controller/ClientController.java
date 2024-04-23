package com.soulcode.sistemachamadosdois.controller;

import com.soulcode.sistemachamadosdois.model.Client;
import com.soulcode.sistemachamadosdois.model.Ticket;
import com.soulcode.sistemachamadosdois.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/register-client")
    public String returnPageRegisterClient(Model model){
        model.addAttribute("client", new Client());
       return "register-client";
    }

    @PostMapping("/register-client")
    public String createClient(@ModelAttribute("client") Client client, RedirectAttributes redirectAttributes) {
        try {
            // cria o cliente dentro do bd
            clientService.createClient(client);
            redirectAttributes.addFlashAttribute("registrationSuccess", true);
            return "redirect:/login"; // Redireciona para a página de login
        } catch (Exception e) {
            // em caso de erro
            redirectAttributes.addAttribute("error", true);
            return "redirect:/register-client";
        }
    }

    @GetMapping("/dashboard-user")
    public String showTicketsForClient(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Recupera o cliente da sessão
        Optional<Client> clientDb = clientService.getClientByEmail(userDetails.getUsername());

        // Adiciona o cliente ao modelo
        model.addAttribute("client", clientDb);

        // Adiciona o atributo name no html
        model.addAttribute("name", clientDb.get().getName());

        // Recupera os tickets do cliente
        List<Ticket> tickets = clientService.getTicketById(clientDb.get().getUserId());

        // Verifica se a lista de tickets é nula ou vazia
        if (tickets == null || tickets.isEmpty()) {
            // Se não houver tickets, cria uma lista vazia
            tickets = new ArrayList<>();
        }

        // Adiciona os tickets ao modelo
        model.addAttribute("tickets", tickets);

        return "dashboard-user";
    }


}
