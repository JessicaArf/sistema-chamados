package com.soulcode.sistemachamadosdois.controller;

import com.soulcode.sistemachamadosdois.model.Client;
import com.soulcode.sistemachamadosdois.model.Ticket;
import com.soulcode.sistemachamadosdois.service.TicketService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/create-ticket")
    public String returnPageCreateTicket(Model model){
        model.addAttribute("ticket", new Ticket());
        return "create-ticket";
    }

    @PostMapping("/create-ticket")
    public String createTiclet(@ModelAttribute("ticket") Ticket ticket, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            Client client = (Client) session.getAttribute("client");
            ticketService.createTicket(ticket, client);
            redirectAttributes.addFlashAttribute("registrationSuccess", true);
            return "redirect:/dashboard-user"; // Redireciona para a página de login
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/create-ticket";
        }
    }
}
