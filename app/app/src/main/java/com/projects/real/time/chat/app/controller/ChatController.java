package com.chat.app.controller;

import com.chat.app.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    @MessageMapping("/sendMessage") //when a client sent message to the endpoint /app/sendMessages. the mapMapping will be called
    @SendTo("/topic/messages") // this tells sb to return messages to all the clients subscribed to the topic/messages
    public ChatMessage sendMessage(ChatMessage message){
        return message;
    }

    @GetMapping("chat")
    public String chat() {
        return "chat"; // to return thymeleaf template
    }
}
