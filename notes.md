# 📡 Real-Time Chat Application – Notes

A Spring Boot + WebSocket-based real-time chat app using STOMP, SockJS, and HTML/JS.

---

## 📌 Purpose

Enable real-time, bi-directional messaging between browser clients and server using WebSocket protocol and STOMP messaging.

---

## 🛠️ Technologies Used

| Layer     | Tools |
|-----------|-------|
| Frontend  | HTML, Bootstrap, JavaScript, SockJS, STOMP.js |
| Backend   | Spring Boot, WebSocket, STOMP, Thymeleaf |
| Protocol  | WebSocket (fallback: SockJS) |
| Messaging | STOMP (`/app/sendMessage`, `/topic/messages`) |
| Java Model | ChatMessage POJO (`sender`, `content`) |

---

## ⚙️ Technology Purposes

- **Spring Boot** – Backend server & WebSocket config  
- **WebSocket** – Real-time, full-duplex communication  
- **SockJS** – Fallback support for browsers lacking native WebSocket  
- **STOMP** – Messaging protocol on top of WebSocket  
- **Thymeleaf** – View rendering (optional)

---

## 🔄 Architecture Overview

[ Client Browser ] <--- SockJS/WebSocket ---> [ Spring Boot Server ]
| |
| --- send message to /app/sendMessage ---> |
| <--- receive from /topic/messages --------|



---

## 🌐 WebSocket Basics

- Long-lived, full-duplex TCP connection.
- Spring Boot WebSocket enabled using:
  ```java
  @EnableWebSocketMessageBroker


Endpoints registered via registerStompEndpoints().

Message routing via @MessageMapping, @SendTo.

📥 SockJS
Fallback layer for non-WebSocket browsers.

Enabled with .withSockJS() on endpoint registration.

Provides transport: XHR streaming, long-polling, etc.

📫 STOMP (Spring Messaging)
Simple text-based protocol over WebSocket.

Clients can:

send messages to /app/*

subscribe to /topic/*

Annotations used:

@MessageMapping("/sendMessage") – from client

@SendTo("/topic/messages") – to clients

💻 JavaScript Integration
STOMP.js + SockJS
Used on frontend to interact with WebSocket STOMP endpoints.

JavaScript Functions
js
Copy
Edit
connect()         // Establish WebSocket connection
sendMessage()     // Send message to /app/sendMessage
showMessage(msg)  // Render incoming message to chat window
🧱 Backend Structure
📄 WebSocketConfig.java
java
Copy
Edit
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    public void registerStompEndpoints(...) {
        registry.addEndpoint("/chat").withSockJS();
    }
    
    public void configureMessageBroker(...) {
        broker.enableSimpleBroker("/topic");
        broker.setApplicationDestinationPrefixes("/app");
    }
}
📄 ChatController.java
java
Copy
Edit
@MessageMapping("/sendMessage")
@SendTo("/topic/messages")
public ChatMessage sendMessage(ChatMessage message) { ... }

@GetMapping("/chat")
public String chatPage() { return "chat.html"; }
📄 ChatMessage.java
java
Copy
Edit
@Data
@NoArgsConstructor
public class ChatMessage {
    private String sender;
    private String content;
}
🧠 Conceptual Mapping
Concept	Analogy
WebSocket	Open phone line
STOMP	Communication protocol
Topic	Chatroom/channel
Message Broker	Post office/router
SockJS	Fallback carrier pigeon 🙂
STOMP.js	JS client for WebSocket STOMP

🧭 Flow Summary
Browser loads /chat (via Thymeleaf)

JS connects to /chat endpoint using SockJS + STOMP.js

User sends message → /app/sendMessage

Server receives via @MessageMapping

Server broadcasts to /topic/messages

All subscribed clients receive and render it

➕ Advanced (Multi-Room Support)
Use different topics:

/topic/room1

/topic/room2

Allow dynamic room joins and subscriptions

🧪 Testing Tips
Open in 2 browser tabs to test live updates

Disable WebSocket in browser DevTools to test SockJS fallback

📚 References
Spring Boot WebSocket: https://spring.io/guides/gs/messaging-stomp-websocket/

STOMP Protocol: https://stomp.github.io/

SockJS: https://github.com/sockjs/sockjs-client

STOMP.js: https://github.com/stomp-js/stompjs
