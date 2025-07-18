WebSocket
1. Websocket is a protocol for full-duplex, real-time communication between client and server over a single, long-lived TCP connection. 
2. In Spring Boot, WebSocket support is provided via the spring-boot-starter-websocket dependency.
3. We can enable web socket support by annotating a configuration class with @EnableWebSocketMessageBroker and extending AbstractWebSocketMessagingBrokerConfigurer.
4. Websocket endpoints are registered using registerStompEndpoints.

Spring Messaging (STOMP protocol)
1. STOMP(Simple Text Oriented Messaging Protocol) is a simple messaging protocol that defines a format for client-server messaging.
2. Spring uses STOMP over websockets to allow clients to subscribe to topics and send messages to destinations.
3. In Spring, we use @MessageMapping for handling messages from clients and @SendTo to broadcast messages to subscribers.
4. The broker relay can be simple (in-memory) or external (like RabbitMQ or ActiveMQ).

SockJS
1. SockJS is a javascript library(with server-side support) that provides a websocket-like API but fallsback to HTTP based transport (like XHR, long polling) if websocket is not available.
2. In Spring boot, enabling sockjs is as simple as calling .withSockJS() when registering your endpoint.
3. This ensures compatibility with browsers or networks that do not support native websocket.

STOMP.js
1. STOMP.js is a javascript client library for STOMP messaging over Websocket(or SOCKJS)
2. It allows the browser to connect to a STOMP endpoint, subscribe to topics and send messages.

* WebSocket enables real-time bidirectional communication.
* Spring boot integrates WebSocket with STOMP for messaging semantics.
* SOCKJS provides fallbacks options for broader compatibility.
* STOMP.JS is the client-side library for interacting with STOMP endpoints from Javascript.
