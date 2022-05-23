package com.bip.blockchainadapter.repositories;

import com.bip.blockchainadapter.models.entities.Invoice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class InvoiceRepository extends ReactiveMongoRepository<Invoice, String> {

    @Override
    public <S extends Invoice> Mono<S> save(S entity) {
        return null;
    }

    @Override
    public Mono<Invoice> findById(String s) {
        return null;
    }
}
