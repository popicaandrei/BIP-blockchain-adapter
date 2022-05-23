package com.bip.blockchainadapter.messaging;

import com.bip.blockchainadapter.config.exceptions.BusinessException;
import com.bip.blockchainadapter.models.messaging.EventPayload;
import com.bip.blockchainadapter.services.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


@Component
@Slf4j
public class RabbitConsumer {
    @Autowired
    EventService eventService;

    @RabbitListener(queues = {"${spring.rabbitmq.queue}"})
    public void receive(Message<EventPayload> message) {
        try {
            var payload = message.getPayload();
            Flux.just(payload)
                    .log()
                    .subscribe(s -> eventService.handleEvents(payload));

            log.info("Event: {} received for user: {}", payload.getEventName(), payload.getUser().getName());
        } catch (Exception ex) {
            log.error("Could not consume event");
            throw new BusinessException("Event listener error");
        }
    }
}
