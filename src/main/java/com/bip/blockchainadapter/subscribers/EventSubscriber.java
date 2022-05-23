package com.bip.blockchainadapter.subscribers;

import com.bip.blockchainadapter.models.messaging.EventPayload;
import com.bip.blockchainadapter.services.ElrondService;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;

public class EventSubscriber implements Subscriber<EventPayload> {

    @Autowired
    ElrondService elrondService;

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(EventPayload event) {
        var transHash = elrondService.sendTransaction(event);
        subscription.request(1);
        // mongo with event and hash
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("On error " + throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("On complete ");
    }
}
