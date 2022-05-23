package com.bip.blockchainadapter.services;

import com.bip.blockchainadapter.models.entities.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendInvoice(Invoice invoice) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Invoice for Event " + invoice.getEvent());
            helper.setFrom("andrei.dummy1234@gmail.com");
            helper.setTo("popica.andreivlad@gmail.com");

            helper.setText(createMailContent(invoice), true);
            File resource = ResourceUtils.getFile("classpath:data/citizens.png");
            helper.addInline("citizens", resource);

            emailSender.send(message);
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }

    public String createMailContent(Invoice invoice) {
        return "<b>Dear citizen</b>," +
                "<br><b>Here is the invoice for the event: " + invoice.getEvent() + "</b>" +
                "<br>" +
                "<br><img src='cid:image001'/><br><b>Best Regards" +
                "<br> Reward received: " + invoice.getReward() +
                "<br> Authenticated via: " + invoice.getAuthType().name() +
                "<br> Authentication identifier: " + invoice.getAuthType().name() +
                "<br> Transaction identifier: " + invoice.getTransactionIdentifier() +
                "<br> Wallet identifier: " + invoice.getInvoiceData().getWalletAddress() +
                "<br> Institution: " + invoice.getInstitution() +
                "<br> Event date: " + invoice.getEventDate() +
                "<br> <img src='cid:citizens'/>" +
                "<br><b>Thank you for being part of " + invoice.getInstitution() + "'s life</b>";
    }

}
