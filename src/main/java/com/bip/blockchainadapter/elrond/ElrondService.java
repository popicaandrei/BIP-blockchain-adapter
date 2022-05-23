package com.bip.blockchainadapter.elrond;

import com.bip.blockchainadapter.models.messaging.EventPayload;
import com.bip.blockchainadapter.utils.WalletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.crldev.elrondspringbootstarterreactive.api.model.TransactionHash;
import software.crldev.elrondspringbootstarterreactive.domain.account.Address;
import software.crldev.elrondspringbootstarterreactive.domain.common.Balance;
import software.crldev.elrondspringbootstarterreactive.domain.transaction.PayloadData;
import software.crldev.elrondspringbootstarterreactive.domain.wallet.Wallet;
import software.crldev.elrondspringbootstarterreactive.interactor.transaction.ErdTransactionInteractor;
import software.crldev.elrondspringbootstarterreactive.interactor.transaction.TransactionRequest;

import java.util.Objects;

@Service
@Slf4j
public class ElrondService {

    @Autowired
    ErdTransactionInteractor interactor;

    public Mono<TransactionHash> sendTransaction(EventPayload event) {
        var wallet = createInstitutionWallet();
        var transactionPayload = createTransactionRequest(wallet, event);
        log.info(transactionPayload.toString());
        var hash = interactor.sendTransaction(wallet, transactionPayload);
        hash.log().subscribe(s -> System.out.println("Transaction request"));
        return hash;
    }

    private TransactionRequest createTransactionRequest(Wallet wallet, EventPayload event){
        return TransactionRequest.builder()
                .receiverAddress(Address.fromBech32("erd1hfw4zhllexu4mys02hyj25nu5vuerp8mczhgzuz8ckp74q6muxrs6s2tt0"))
                .data(PayloadData.fromString("Transaction for event " + event.getEventName()))
                .value(Balance.fromEgld(event.getReward()))
                .build();
    }

    private Wallet createInstitutionWallet() {
        return Wallet.fromMnemonic(Objects.requireNonNull(WalletUtil.getMnemonics()), 0);
    }
}
