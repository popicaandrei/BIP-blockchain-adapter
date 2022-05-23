package com.bip.blockchainadapter.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final boolean SSL_FLAG = true;


    public void sendSimpleEmail() {
        String userName = "andrei.dummy1234@gmail.com";
        String password = "0Pass2468&";

        String fromAddress = "andrei.dummy1234@gmail.com";
        String toAddress = "popica.andreivlad@gmail.com";
        String subject = "Test Mail";
        String message = "Hello from Apache Mail";

        try {
            Email email = new SimpleEmail();
            email.setHostName(HOST);
            email.setSmtpPort(PORT);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setSSLOnConnect(SSL_FLAG);
            email.setFrom(fromAddress);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(toAddress);
            email.send();
        } catch (Exception ex) {
            System.out.println("Unable to send email");
            System.out.println(ex);
        }
    }





//    @Autowired
//    private JavaMailSender emailSender;
//
//    private final Session emailSession;
//    private final String EMAIL_USERNAME = "andrei.dummy1234@gmail.com";
//    private final String EMAIL_PASSWORD = "0Pass2468&";
//
//    public EmailService() {
//        //email setup
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        this.emailSession = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
//                    }
//                });
//    }
//
//    public void sendInvoice(Invoice invoice) {
//        try {
//            MimeMessage message = new MimeMessage(this.emailSession);
//
//            message.setFrom(new InternetAddress(EMAIL_USERNAME));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("popica.andreivlad@gmail.com"));
//            message.setSubject("ceva");
//
////            MimeMessageHelper helper = new MimeMessageHelper(message, true);
////            helper.setSubject("Invoice for Event " + invoice.getEvent());
////            helper.setFrom("andrei.dummy1234@gmail.com");
////            helper.setTo("popica.andreivlad@gmail.com");
////
////            helper.setText(createMailContent(invoice), true);
////            File resource = ResourceUtils.getFile("classpath:static/citizens.png");
////            helper.addInline("citizens", resource);
//
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.getMessage();
//        }
//    }
//
//    public String createMailContent(Invoice invoice) {
//        return "<b>Dear citizen</b>," +
//                "<br><b>Here is the invoice for the event: " + invoice.getEvent() + "</b>" +
//                "<br>" +
//                "<br><img src='cid:image001'/><br><b>Best Regards" +
//                "<br> Reward received: " + invoice.getReward() +
//                "<br> Authenticated via: " + invoice.getAuthType().name() +
//                "<br> Authentication identifier: " + invoice.getAuthType().name() +
//                "<br> Transaction identifier: " + invoice.getTransactionIdentifier() +
//                "<br> Wallet identifier: " + invoice.getInvoiceData().getWalletAddress() +
//                "<br> Institution: " + invoice.getInstitution() +
//                "<br> Event date: " + invoice.getEventDate() +
//                "<br> <img src='cid:citizens'/>" +
//                "<br><b>Thank you for being part of " + invoice.getInstitution() + "'s life</b>";
//    }

}
