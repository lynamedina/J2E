package tn.pi.ManageRecruitment.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.ManageRecruitment.model.User;
import tn.pi.ManageRecruitment.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String role,
                           Model model) {
        try {
            authService.register(name, email, password, role);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        try {
            User user = authService.login(email, password);
            session.setAttribute("user", user);
            String role = user.getRoles().iterator().next().getName();
            switch (role) {
                case "ADMIN":
                    return "redirect:/admin";
                case "RECRUTEUR":
                    return "redirect:/recruiter";
                case "PARTICIPANT":
                    return "redirect:/applicant";
                default:
                    return "redirect:/";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/?logout=true";
    }
}