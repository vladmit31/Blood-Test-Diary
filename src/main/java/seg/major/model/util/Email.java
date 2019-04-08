package seg.major.model.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Models basic structure of an e-mail.
 * @author Team Pacane
 * @version 1.0
 */
public class Email {

	private String emailHost;
	private String port;
	private String emailFrom;
	private String emailPassword;
	private String emailTo;
	private String subject;
	private String body;

	/**
	 * Constructs an email with the given fields
	 *
	 * @param emailHost
	 * @param port
	 * @param emailFrom
	 * @param emailPassword
	 * @param emailTo
	 * @param subject
	 * @param body
	 */
	public Email(String emailHost, String port, String emailFrom, String emailPassword, String emailTo, String subject,
			String body) {
		this.emailHost = emailHost;
		this.port = port;
		this.emailFrom = emailFrom;
		this.emailPassword = emailPassword;
		this.emailTo = emailTo;
		this.subject = subject;
		this.body = body;
	}

	public String getEmailHost() {
		return this.emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getEmailFrom() {
		return this.emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailPassword() {
		return this.emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailTo() {
		return this.emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Sends the stored email
	 */
	public void sendEmail() {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", emailHost);
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailFrom, emailPassword);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}