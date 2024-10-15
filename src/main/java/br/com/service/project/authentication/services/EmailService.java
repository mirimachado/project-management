package br.com.service.project.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String remetente;

    public EmailService() {
    }

    public String enviaEmail(String assunto, String destinatario, String mensagem) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(this.remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setText(mensagem);
            simpleMailMessage.setSubject(assunto);
            this.javaMailSender.send(simpleMailMessage);
            return "E-mail enviado";
        } catch (Exception var5) {
            return "Erro ao tentar enviar e-mail.";
        }
    }
}
