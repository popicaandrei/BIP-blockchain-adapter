package com.bip.blockchainadapter.services;

import com.bip.blockchainadapter.config.exceptions.BusinessException;
import com.bip.blockchainadapter.models.entities.Invoice;
import com.bip.blockchainadapter.models.messaging.EventPayload;
import com.bip.blockchainadapter.repositories.InvoiceRepository;
import com.bip.blockchainadapter.utils.converters.InvoiceConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.crldev.elrondspringbootstarterreactive.api.model.TransactionHash;

@Service
@Slf4j
public class EventService {

    @Autowired
    ElrondService elrondService;
    @Autowired
    EmailService emailService;
    @Autowired
    InvoiceRepository invoiceRepository;

    @Transactional
    public void handleEvents(EventPayload payload) {
        log.info("Event is handled, sending it to Elrond interactor");
        elrondService.sendTransaction(payload)
                .doOnError(throwable -> {
                    throw new BusinessException("Error appeared when sending transaction");
                })
                .subscribe(hash -> createInvoice(payload, hash));
    }

    private void createInvoice(EventPayload event, TransactionHash hash) {
        Invoice invoice = InvoiceConvertor.convertEventToInvoice(event);
        invoice.setTransactionIdentifier(hash.getHash());
        log.info("New invoice is being saved in database {}", invoice);
        invoiceRepository.save(invoice)
                .doOnError(throwable -> {
                    throw new BusinessException("Error appeared when saving invoice");
                }).
                subscribe(i -> emailService.sendInvoice(i));
    }
}
