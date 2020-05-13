package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import ru.otus.messagesystem.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static ru.otus.ApplConfig.DATE_TIME_FORMAT;

@Controller
public class TimeWsController {
    private static final Logger logger = LoggerFactory.getLogger(TimeWsController.class);

    private final SimpMessagingTemplate template;

    public TimeWsController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/websocket")
    public void receiveMessage(Message message){
        logger.info("message: {}" + Arrays.toString(message.getPayload()));
    }

    @Scheduled(fixedDelay = 1000)
    public void broadcastCurrentTime() {
        this.template.convertAndSend("/topic/message", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
    }

}
