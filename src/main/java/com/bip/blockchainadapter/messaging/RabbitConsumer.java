package com.bip.blockchainadapter.messaging;

import com.bip.blockchainadapter.config.exceptions.BusinessException;
import com.bip.blockchainadapter.elrond.ElrondService;
import com.bip.blockchainadapter.models.messaging.EventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RabbitConsumer {
    @Autowired
    ElrondService elrondService;

    @RabbitListener(queues = {"${spring.rabbitmq.queue}"})
    public void receive(Message<EventPayload> message) {
        try {
            var payload = message.getPayload();

            elrondService.sendTransaction(payload);

//            Flux.just(payload)
//                    .log()
//                    .subscribe(s -> elrondService.sendTransaction(payload));

            log.info("Event: {} received for user: {}", payload.getEventName(), payload.getUser().getName());
        } catch (Exception ex) {
            log.error("Could not consume event");
            throw new BusinessException("Event listener error");
        }
    }
}
