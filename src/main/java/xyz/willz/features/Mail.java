package xyz.willz.features;import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {

    public void sendMail(String send_to) {
    	
Properties properties = new Properties();
		
		properties.put("mail.smtp.auth",true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
    	
		final String email = "princewillz2013@gmail.com";
		final String password = "@MSDabangg2013";
		
		try {
			Session session = Session.getInstance(properties, new Authenticator() {
	
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});
	
		Message message = prepareMessage(session, email, send_to);
		
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	} catch(Exception e) {
		System.out.println(e.getMessage());
	}
    	
    	
    	
    	
    	/* try {
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("calmbulbul22@gmail.com"));
        Properties properties = new Properties(); 		 	
        properties.put("mail.smtp.auth",true); 		
        properties.put("mail.smtp.starttls.enable", true); 		properties.put("mail.smtp.host", "smtp.gmail.com"); 		properties.put("mail.smtp.port", "587");
        message.setRecipient(RecipientType.TO, new InternetAddress(send_to));
        message.setSubject("Notification");
        message.setText("Successful!", "UTF-8"); // as "text/plain"
        message.setSentDate(new Date());
        Transport.send(message);
    	} catch(Exception e) {
    		System.out.println(e);
    	} */
    }
    
    private static Message prepareMessage(Session session, String myAccount, String receiver) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject("Kaa re BullBulliya");
			message.setText("Hii Hellow");
			return message;
		} catch(Exception e) {
			System.out.println("error");
		}
		return null;
	}

}