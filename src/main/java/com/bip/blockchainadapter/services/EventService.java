package com.bip.blockchainadapter.services;

import com.bip.blockchainadapter.config.exceptions.BusinessException;
import com.bip.blockchainadapter.models.entities.Invoice;
import com.bip.blockchainadapter.models.messaging.EventPayload;
import com.bip.blockchainadapter.repositories.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EventService {

    @Autowired
    ElrondService elrondService;
    @Autowired
    InvoiceRepository invoiceRepository;

    @Transactional
    public void handleEvents(EventPayload payload) {
        log.info("Event is handled, sending it to enrond client");

        elrondService.sendTransaction(payload)
                .doOnError(throwable -> {
                    throw new BusinessException("Error appeared when sending transaction");
                })
                .subscribe(s -> createInvoice());
    }

    private Mono<Invoice> createInvoice() {
        return null;
    }
}
