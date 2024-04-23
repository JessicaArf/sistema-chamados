package com.soulcode.sistemachamadosdois.controller;

import com.soulcode.sistemachamadosdois.model.Client;
import com.soulcode.sistemachamadosdois.model.Ticket;
import com.soulcode.sistemachamadosdois.service.ClientService;
import com.soulcode.sistemachamadosdois.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final ClientService clientService;

    @GetMapping("/create-ticket")
    public String returnPageCreateTicket(Model model){
        model.addAttribute("ticket", new Ticket());
        return "create-ticket";
    }

    @PostMapping("/create-ticket")
    public String createTicket(@ModelAttribute("ticket") Ticket ticket, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            // pega o email do usuário autenticado e procura o usuário por ele
            Client clientDb = clientService.getClientByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            // cria o ticket e salva o cliente dentro do ticket
            ticketService.createTicket(ticket, clientDb);
            redirectAttributes.addFlashAttribute("registrationSuccess", true);
            return "redirect:/dashboard-user"; // Redireciona para a página de login
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/create-ticket";
        }
    }

}
