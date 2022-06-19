package com.bip.blockchainadapter.services;

import com.bip.blockchainadapter.models.entities.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class EmailService {
    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final boolean SSL_FLAG = true;
    private final String EMAIL_USERNAME = "";
    private final String EMAIL_PASSWORD = "";

    public void sendInvoice(Invoice invoice) {

        log.info("Sending new email for invoice with number {}", invoice.getId());
        try {
            HtmlEmail email = new HtmlEmail();
            StringBuffer msg = new StringBuffer();
            email.setHostName(HOST);
            email.setSmtpPort(PORT);
            email.setAuthenticator(new DefaultAuthenticator(EMAIL_USERNAME, EMAIL_PASSWORD));
            email.setSSLOnConnect(SSL_FLAG);
            email.setFrom(EMAIL_USERNAME);
            email.setSubject("Invoice for Event " + invoice.getEvent());

            File citizensPicture = ResourceUtils.getFile("classpath:static/citizens.png");
            msg.append("<img src=cid:").append(email.embed(citizensPicture)).append(">");
            email.setHtmlMsg(createMailContent(invoice, String.valueOf(msg)));

            email.addTo(invoice.getInvoiceData().getEmail());
            email.send();
        } catch (Exception ex) {
            log.error("Unable to send email, exception: {}", ex.getMessage());
        }
    }

    public String createMailContent(Invoice invoice, String picture) {
        return "<b>Dear citizen</b>," +
                "<br>" +
                "<br><b>Here is the invoice for your event: " + invoice.getEvent() + "</b>" +
                "<br><b>Reward received: </b>" + invoice.getReward() + " EGLD" +
                "<br><b>Authentication identifier: </b>" + invoice.getIdentificator() +
                "<br><b>Transaction identifier: </b>" + invoice.getTransactionIdentifier() +
                "<br><b> Wallet identifier: </b>" + invoice.getInvoiceData().getWalletAddress() +
                "<br><b>Institution: </b>" + invoice.getInstitution() +
                "<br><b> Event date: </b>" + dateFormatter(invoice.getEventDate()) +
                "<br>" +
                "<br><b>Thank you for being part of " + invoice.getInstitution() + "'s life</b>" +
                "<br>" + picture;
    }

    private String dateFormatter(Date date) {
        DateFormat inputFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return inputFormatter.format(date);
    }
}
