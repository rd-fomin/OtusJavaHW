package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @MessageMapping("/message")
    public void receiveMessage(Long message){
        frontendService.getUserData(message, s -> logger.info("Received message: {}", s));
    }

    public void getUserList(Model model) {
        this.template.convertAndSend("/topic/message", "Hall");
    }

}
