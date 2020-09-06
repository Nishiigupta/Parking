package xyz.willz.features;import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Mail extends HttpServlet {

    public void sendMail(HttpServletRequest request, HttpServletResponse response) {
    	
    	
    	try {
       
			final String from = "calmbulbul22@gmail.com";
	        final String password = "Bittu.22";
	        String to = "nishigupta726@gmail.com";
	        String subject = "Congratulations your reg has been sucessuffull reg";
	        String message = "Hii";

	        Properties properties = System.getProperties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.port", "587");

	        if (from.contains("hotmail")) {
	            properties.put("mail.smtp.host", "smtp.live.com");

	        } else if (from.contains("gmail")) {
	            properties.put("mail.smtp.host", "smtp.gmail.com");

	        } else if (from.contains("yahoo")) {
	            properties.put("mail.smtp.host", "smtp.mail.yahoo.com");

	        } else {
	            System.out.println("<h2 style='color:red;'>Unknown domain name!!</h2><br>"
	                    + "<p>Please use 'Yahoo or Gmail or Hotmail'</p>");
	        }
	        Session session2 = Session.getDefaultInstance(properties, new javax.mail.Authenticator()
	        {
	            public javax.mail.PasswordAuthentication getPasswordAuthentication()
	            {
	            	
	                return new javax.mail.PasswordAuthentication(from,password);
	            }
	        });

	        try {
	            MimeMessage mimeMessage = new MimeMessage(session2);
	            mimeMessage.setFrom(new InternetAddress(from));
	           
	            
	           	 mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	                mimeMessage.setSubject(subject);
	                mimeMessage.setText(message);

	                Transport.send(mimeMessage);
	     
	            
	            
	        } catch (MessagingException e) {
	            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");

	            System.out.println("<h2 style='color:red;'>Fatal Error!!</h2><br>" + "<p>" + e.getMessage() + "</p>");
	        }
	    	 } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 

	    
    
   
	}

}