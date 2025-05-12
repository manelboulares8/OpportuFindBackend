package com.project.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public String formatDateTime(String dateTime) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy 'à' hh:mm a");
        return localDateTime.format(formatter);
    }

    public void sendRecruitmentResult(String candidateEmail, String status, String recruitmentDate, String offerName) {
        // Traduction du statut
        if ("APPROVED".equals(status)) {
            status = "Approuvé";
        } else if ("REJECTED".equals(status)) {
            status = "Rejeté";
        } else if ("PENDING".equals(status)) {
            status = "En attente";
        }

        String emailSubject = "Mise à jour du statut de votre candidature pour le stage '" + offerName + "' : " + status;
        String emailBody = "";

        if ("Approuvé".equals(status)) {
            String formattedDate = formatDateTime(recruitmentDate);
            emailBody = String.format(
                "Cher(e) candidat(e),\n\n" +
                "Nous avons le plaisir de vous informer que votre candidature pour le stage '%s' a été %s. " +
                "Nous vous félicitons pour cette réussite et sommes impatients de collaborer avec vous.\n\n" +
                "Votre date de début est confirmée pour le : %s.\n\n" +
                "N'hésitez pas à nous contacter si vous avez des questions ou besoin de plus d'informations. " +
                "Nous sommes ravis de vous accueillir chez OpportuFind.\n\n" +
                "Cordialement,\n" +
                "L'équipe de recrutement\n" +
                "OpportuFind\n",
                offerName, status, formattedDate
            );
        } else if ("Rejeté".equals(status)) {
            emailBody = String.format(
                "Cher(e) candidat(e),\n\n" +
                "Merci de l'intérêt que vous avez porté à OpportuFind en postulant au stage '%s'. Nous avons le regret de vous informer que votre candidature a été %s. " +
                "Bien que nous ne puissions pas donner une suite favorable à votre candidature pour cette fois, nous vous remercions pour votre temps et vos efforts.\n\n" +
                "Nous vous souhaitons plein de succès dans vos futurs projets et espérons recevoir votre candidature pour d'autres opportunités chez OpportuFind.\n\n" +
                "Cordialement,\n" +
                "L'équipe de recrutement\n" +
                "OpportuFind\n",
                offerName, status
            );
        } else if ("En attente".equals(status)) {
            emailBody = String.format(
                "Cher(e) candidat(e),\n\n" +
                "Merci de l'intérêt que vous avez porté à OpportuFind en postulant au stage '%s'. Votre candidature est actuellement en %s. " +
                "Nous analysons encore les différentes candidatures reçues et reviendrons vers vous dès que possible avec une réponse.\n\n" +
                "Nous vous remercions pour votre patience et restons à votre disposition si vous avez des questions.\n\n" +
                "Cordialement,\n" +
                "L'équipe de recrutement\n" +
                "OpportuFind\n",
                offerName, status
            );
        }

        // Envoi de l'email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(candidateEmail);
        message.setSubject(emailSubject);
        message.setText(emailBody);
        emailSender.send(message);
    }
}
