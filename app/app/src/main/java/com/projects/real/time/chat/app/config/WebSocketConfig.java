package com.chat.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
// WebSocketMessageBroker defines the methods for configuring message handling with simple messaging protocols (eg STOMP) from websocket clients.
// message broking is like different routings and websocket enables to direct messages to the right direction. for eg different chat rooms in meetings
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // localhost:8080/chat
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS(); // using sockjs for the fallback
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // set message broker for topic route i.e if we have /topic/chatRoom1 eeryone subscribed to the topic or chatroom1 will get messages
        registry.enableSimpleBroker("/topic");
        // expect message with /app/sendmessage --> in the below line we are saying the server that any message that comes with /app prefix to process it.
        registry.setApplicationDestinationPrefixes("/app");

    }
}
