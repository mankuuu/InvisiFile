package service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendOTP {
    public static void sendOTP(String email , String generatedOTP) {
        String to = email;
        String from = "kkrhbaldev@gmail.com";
        String host = "smtp.gmail.com";

        //Getting System Properties
        Properties properties = System.getProperties();

        //Setting up the Mail Server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() { // Use jakarta.mail.Authenticator

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "xykacnwuspeimxwz"); // Your password here
            }
        });

        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("OTP for InvisiFile");

            // Now set the actual message
            message.setText("Your One time Password for InvisiFile app is " + generatedOTP);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
