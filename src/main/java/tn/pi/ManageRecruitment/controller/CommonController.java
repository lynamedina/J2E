package tn.pi.ManageRecruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/")
    public String home() {
        return "home"; // Correspond à home.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Correspond à login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Correspond à register.html
    }
}