Real-Time Chat Application

Purpose
Build a real-time chat application where users can send and receive messages instantly via webSockets, using Spring Boot(backend) and HTML/JS (frontend).

Technologies Used
Layer    --------        Tools
Frontend ------ HTML, Bootstrap, Javascript, SockJS, STOMP
Backend ---- Spring Boot, WebSocket, STOMP, Thymeleaf(view)
Protocol --- Websocket over SockJS fallback
Messaging ---- STOMP(/app/sendMessage, /topic/messages )
Java Model --- ChatMessage POJO(sender,content)

Technology --- purpose
Spring Boot -- Backend Server and WebSocket config
WebSocket + STOMP --> Real-time communication protocol
SockJS --> Fallback for older browsers without native WebSocket Support
STOMP --> Messaging protocol over WebSocket (like HTTP for sockets)
Thymeleaf(optional) ---> Renders HTML views in Spring Boot


[Client Browser ] <------ WebSocket/SockJS   ---------> [Spring Boot Server]
       |                                                    |
       |  -------- send message via STOMP ----------------->|
       |  <------- broadcast message to topic --------------|


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

** JS Functions **
connect()
* Creates a Connection to /chat endpoint via SockJS
* Subscribes to /topic/messages topic
*  Sets button enabled when connected

sendMessage()
*  Reads sender and content from input
*  Send message to /app/sendMessage
*  Clears input after sending

showMessage()
*  creates a new <div> with sender:content
*  Appends it to the #chat window


Backend

WebSocketConfig.java
* @EnableWebSocketMessageBroker  --> enables use of STOMP over websockets
* registerStompEndpoints --> Register the endpoint /chat for clients to connect and adds SockJs fallback
* configureMessagebroker --> Enables simple broker at /topic and sets aapplication destination prefix /app

* So:
* /app/sendMessages -> sent from client
* /topic/messages -> Subscribed by clients

ChatController.java
* @MessageMapping("/sendMessage") --> this listens message send to the /app/sendMessage
* @SendTo("/topic/messages") --> Broadcasts the received message to everyone subscribed to /topic/messages
* @GetMapping("/chat") --> Loads the chat.html page if using Thymeleaf

ChatMessage.java (Model)
* A simple POJO (Java Object) that holds:
* id (optional)
* Sender -> Name of the user
* content -> MEssage Text
Uses:
* @Data from Lombok for getters/setters
* @NoArgsConstructor for object creation

Concept ---> Real world Annalogy
WebSocket --> open phone line (bi-directional)
STOMP --> Rules/protocol for sending/receiving messages (like HTTP over TCP)
Message Broker --> Post office - routes messages to correct topics
Topic --> Chatroom/channel where all users subscribed get the messages
SockJS --> Compatiblity layer for older browsers that dont support WebSocket
STOMP Client -> Javascript library that formats and send STOMP messages

Flow Summary
1. Browser opens /chat
2. JS connects to /chat using SockJS
3. When user sends a message :
   * Sent to /app/sendMessage via STOMP
   * Server method sendMessages() receives it
   * Server broadcasts it to /topic/messages
   * All clients receive and display it.
4. Multiple chat rooms /topic/room1, /topic/room2 



