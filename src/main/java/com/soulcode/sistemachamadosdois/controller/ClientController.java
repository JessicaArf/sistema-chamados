package com.soulcode.sistemachamadosdois.controller;

import com.soulcode.sistemachamadosdois.model.Client;
import com.soulcode.sistemachamadosdois.model.Ticket;
import com.soulcode.sistemachamadosdois.service.ClientService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


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
            clientService.createClient(client);
            redirectAttributes.addFlashAttribute("registrationSuccess", true);
            return "redirect:/login"; // Redireciona para a página de login
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/register-client";
        }
    }

    @GetMapping("/dashboard-user")
    public String showTicketsForClient(Model model, HttpSession session) {
        // Recupera o cliente da sessão
        Client client = (Client) session.getAttribute("client");

        // Se não estiver autenticado, redireciona para a página de login
        if (client == null) {
            return "redirect:/login";
        }

        // Adiciona o cliente ao modelo
        model.addAttribute("client", client);

        // Recupera os tickets do cliente
        List<Ticket> tickets = clientService.getTicketById(client.getUserId());

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
