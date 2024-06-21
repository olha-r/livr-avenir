package co.simplon.livravenir.services.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderHelper {

    private final JavaMailSender javaMailSender;

    @Value("${livravenir.mail.sender}")
    private String fromEmail;
    @Value("${livravenir.auth.activationPath}")
    private String activationPath;

    public MailSenderHelper(JavaMailSender javaMailSender) {
	this.javaMailSender = javaMailSender;
    }

    public void sendMail(String userEmail,
	    String confirmationToken) {
	SimpleMailMessage email = new SimpleMailMessage();
	email.setTo(userEmail);
	email.setFrom(fromEmail);
	email.setSubject(
		"Terminer la validation du compte - Livr'Avenir");
	email.setText(
		"Veuillez cliquer sur le lien ci-dessous pour vérifier votre inscription: \n "
			+ activationPath
			+ confirmationToken);
	try {
	    this.javaMailSender.send(email);

	} catch (MailException e) {
	    System.out.println("Error Sending Email: "
		    + e.getMessage());
	}
    }
}
