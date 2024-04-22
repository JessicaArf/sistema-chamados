package com.soulcode.sistemachamadosdois.controller;

import com.soulcode.sistemachamadosdois.dtos.RequestDTO;
import com.soulcode.sistemachamadosdois.model.Client;
import com.soulcode.sistemachamadosdois.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

   // @ModelAttribute("requestDto") RequestDTO requestDto, Model model

    @GetMapping
    public String showLoginForm() {
       // model.addAttribute("requestDto", requestDto);
        return "login";
    }

    /*
    @PostMapping
    public String login(@ModelAttribute("requestDto") RequestDTO requestDto, RedirectAttributes redirectAttributes, HttpSession session) {
        Optional<Client> user = userRepository.findByEmail(requestDto.email());
        if (user.isEmpty() || !user.get().isLoginCorrect(requestDto, passwordEncoder)) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/login";
        } else {
            // Defina o cliente na sessão
            session.setAttribute("client", user.get());
            return "redirect:/dashboard-user";
        }
    }
     */

}
