package tn.pi.ManageRecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.pi.ManageRecruitment.service.EmailService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    // Endpoint pour envoyer une notification par email
    @PostMapping("/send")
    public String sendNotification(@RequestParam String toEmail, @RequestParam String message) {
        try {
            emailService.sendConformiteNotification(toEmail, message);
            return "Notification envoyée avec succès à " + toEmail;
        } catch (Exception e) {
            return "Erreur lors de l'envoi de la notification : " + e.getMessage();
        }
    }
}