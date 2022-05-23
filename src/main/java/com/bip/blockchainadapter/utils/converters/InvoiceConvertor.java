package com.bip.blockchainadapter.utils.converters;

import com.bip.blockchainadapter.models.entities.Invoice;
import com.bip.blockchainadapter.models.messaging.EventPayload;

public class InvoiceConvertor {

    public static Invoice convertEventToInvoice(EventPayload event) {
        return Invoice.builder()
                .event(event.getEventName())
                .authType(event.getEventAuthType())
                .invoiceData(event.getUser())
                .identificator(event.getIdentificator())
                .reward(event.getReward())
                .institution(event.getInstitutionName())
                .eventDate(event.getTimestamp())
                .build();
    }
}
