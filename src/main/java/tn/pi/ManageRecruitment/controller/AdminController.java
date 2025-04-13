package tn.pi.ManageRecruitment.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminPage(Model model) {
        // Ajouter des attributs au modèle si nécessaire
        return "admin";
    }
}