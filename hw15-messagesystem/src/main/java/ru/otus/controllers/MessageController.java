package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.domain.User;
import ru.otus.front.FrontendService;

@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final SimpMessagingTemplate template;
    private final FrontendService frontendService;

    public MessageController(SimpMessagingTemplate template, FrontendService frontendService) {
        this.template = template;
        this.frontendService = frontendService;
    }

    @MessageMapping("/messages/create")
    public void createUser(User user) {
        frontendService.createUser(user, s -> {
            logger.info("Received message: {}", s);
        });
        frontendService.getAll(x -> {
            logger.info("Users list: {}", x);
            template.convertAndSend("/topic/message", x);
        });
    }

}
