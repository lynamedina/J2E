package tn.pi.ManageRecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Méthode pour envoyer une notification par e-mail
    public void sendConformiteNotification(String toEmail, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail); // L'email du destinataire
        mailMessage.setSubject("État de conformité à un poste"); // Sujet de l'email
        mailMessage.setText(message); // Corps du message

        // Envoi de l'email
        mailSender.send(mailMessage);
    }
}
