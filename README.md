# Real-time-chat-feature
A real time chat feature where all users can chat with other members part of that meeting.

## Tools And Technologies

### Backend (Server-side)
* Spring Boot
* Spring WebSocket
* Spring Messaging (STOMP Protocol)
* Thymeleaf

### Frontend (Client-side)
* Thymeleaf
* Javascript (ES6)
* SockJS
* STOMP.js
* HTML/CSS
* Bootstrap

### Development & Infrastructure Tools
* Maven / Gradle

Notes
Websocket is used for continous communication and STOMP (simple messaging protocols) sits on top of websocket is used for architecturing messages like defining how message are organised, routed etc
WebSocketMessageBroker - is like a middle man - directs the messages to the right place.
