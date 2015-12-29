package com.metasocio.authentication;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/******************************************************************************
 * Description: EmailUtil class is to send success
 * message of sign in on Meta-Socio on email-id for a new user
 * 
 ******************************************************************************/
public class EmailUtil {

	/*************************************************************************
	 * Method to send success email to user's email-id after creating profile on
	 * Meta-Socio
	 * 
	 * @param to : email id of new user who is currently logged in
	 * @param subject : email subject
	 * @param emailMessage : email body
	 * @return resultMessage : message response
	 ************************************************************************/
	public String sendEmail(String to, String subject, String emailMessage) {

		String resultMessage; // result message
		String from = "metasocio.social@gmail.com"; // email id of Meta-Socio by which email will be send
		final String username = "metasocio.social@gmail.com"; // user name of Meta-Socio
		final String password = "jmuwqgrzdasxugsq"; // Password of Meta-Socio
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(emailMessage);

			// Send message
			Transport.send(message);

			resultMessage = "Sent message successfully....";

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			resultMessage = "Mail not sent";
			throw new RuntimeException(e);

		}
		return resultMessage; // return result message
	}
}
