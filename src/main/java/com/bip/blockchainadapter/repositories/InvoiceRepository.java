package com.bip.blockchainadapter.repositories;

import com.bip.blockchainadapter.models.entities.Invoice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface InvoiceRepository extends ReactiveMongoRepository<Invoice, String> {

    Flux<Invoice> findByInvoiceData_Email(String email);
}
