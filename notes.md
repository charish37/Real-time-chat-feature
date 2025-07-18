# Real-Time Chat Application with Spring Boot & WebSockets

## Purpose
Build a real-time chat application where users can send and receive messages instantly via WebSockets, using Spring Boot (backend) and HTML/JS (frontend).

---

## Technologies Used

| Layer      | Tools/Tech                          |
|------------|-------------------------------------|
| Frontend   | HTML, Bootstrap, JavaScript, SockJS, STOMP.js |
| Backend    | Spring Boot, WebSocket, STOMP, Thymeleaf (view) |
| Protocol   | WebSocket over SockJS fallback      |
| Messaging  | STOMP (`/app/sendMessage`, `/topic/messages`) |
| Java Model | ChatMessage POJO (`sender`, `content`) |

---

## Technology Purpose

- **Spring Boot:** Backend server and WebSocket configuration.
- **WebSocket + STOMP:** Real-time, full-duplex communication protocol.
- **SockJS:** Fallback for browsers without native WebSocket support.
- **STOMP:** Messaging protocol over WebSocket (like HTTP for sockets).
- **Thymeleaf:** Renders HTML views in Spring Boot (optional).

---

## Architecture Diagram

```
[Client Browser] <--- WebSocket/SockJS ---> [Spring Boot Server]
       |                                         |
       | -- send message via STOMP -------------> |
       | <--- broadcast message to topic -------- |
```

---

## Key Concepts

### WebSocket
- Protocol for full-duplex, real-time communication over a single TCP connection.
- Spring Boot enables WebSocket via `spring-boot-starter-websocket`.
- Enable with `@EnableWebSocketMessageBroker` and configure endpoints with `registerStompEndpoints`.

### Spring Messaging (STOMP Protocol)
- **STOMP**: Simple Text Oriented Messaging Protocol for client-server messaging.
- Spring uses STOMP over WebSocket for topic-based messaging.
- Use `@MessageMapping` for incoming messages and `@SendTo` for broadcasting.
- Broker relay can be in-memory or external (RabbitMQ, ActiveMQ).

### SockJS
- JavaScript library (with server support) that provides a WebSocket-like API with HTTP fallbacks (XHR, long polling).
- Enable in Spring Boot with `.withSockJS()` on endpoint registration.
- Ensures compatibility with browsers/networks lacking WebSocket support.

### STOMP.js
- JavaScript client library for STOMP messaging over WebSocket/SockJS.
- Allows browser to connect, subscribe, and send messages to STOMP endpoints.

---

## JavaScript Functions (Frontend)

- **connect()**
  - Connects to `/chat` endpoint via SockJS.
  - Subscribes to `/topic/messages`.
  - Enables send button when connected.

- **sendMessage()**
  - Reads sender/content from input.
  - Sends message to `/app/sendMessage`.
  - Clears input after sending.

- **showMessage()**
  - Creates a new `<div>` with `sender: content`.
  - Appends it to the chat window.

---

## Backend Components

### WebSocketConfig.java
- `@EnableWebSocketMessageBroker`: Enables STOMP over WebSockets.
- `registerStompEndpoints`: Registers `/chat` endpoint and adds SockJS fallback.
- `configureMessageBroker`: Enables simple broker at `/topic`, sets `/app` as application prefix.

  - `/app/sendMessage`: Client sends messages here.
  - `/topic/messages`: Clients subscribe here for broadcasts.

### ChatController.java
- `@MessageMapping("/sendMessage")`: Handles messages sent to `/app/sendMessage`.
- `@SendTo("/topic/messages")`: Broadcasts to all subscribers.
- `@GetMapping("/chat")`: Loads chat page (if using Thymeleaf).

### ChatMessage.java (Model)
- POJO with:
  - `sender`: Name of the user.
  - `content`: Message text.
- Uses Lombok for boilerplate code.

---

## Real-World Analogies

- **WebSocket:** Open phone line (bi-directional).
- **STOMP:** Rules/protocol for sending/receiving messages (like HTTP over TCP).
- **Message Broker:** Post office, routes messages to topics.
- **Topic:** Chatroom/channel for group messages.
- **SockJS:** Compatibility layer for older browsers.
- **STOMP Client:** JS library for formatting/sending STOMP messages.

---

## Flow Summary

1. Browser opens `/chat`.
2. JS connects to `/chat` using SockJS.
3. User sends a message:
    - Sent to `/app/sendMessage` via STOMP.
    - Server receives and broadcasts to `/topic/messages`.
    - All clients receive and display the message.
4. Multiple chat rooms possible: `/topic/room1`, `/topic/room2`, etc.

---

## Interview Key Points

- WebSocket enables real-time, bidirectional communication.
- Spring Boot integrates WebSocket with STOMP for messaging.
- SockJS provides fallback for broader compatibility.
- STOMP.js is the client-side library for STOMP endpoints.
- Typical use case: Real-time chat, notifications, collaborative apps.

---

## References

- [Spring WebSocket Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket)
- [STOMP Protocol](https://stomp.github.io/)
- [SockJS](https://sockjs.github.io/)
- [STOMP.js](https://stomp-js.github.io/stomp-websocket/codo/)
