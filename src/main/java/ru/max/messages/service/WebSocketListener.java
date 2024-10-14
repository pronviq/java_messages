// package ru.max.messages.service;

// import java.util.List;
// import java.util.Map;
// import java.util.Set;
// import java.util.concurrent.ConcurrentHashMap;

// import org.springframework.context.event.EventListener;
// import org.springframework.messaging.MessageHeaders;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
// import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
// import org.springframework.stereotype.Component;
// import org.springframework.web.socket.messaging.SessionConnectedEvent;
// import org.springframework.web.socket.messaging.SessionSubscribeEvent;

// import lombok.RequiredArgsConstructor;
// import ru.max.messages.contexts.UserContext;
// import ru.max.messages.contexts.WebSocketContext;

// @Component
// @RequiredArgsConstructor
// public class WebSocketListener {
// 	private static final Map<Long, String> userSessions = new ConcurrentHashMap<>();
// 	private final TokenService tokenService;
// 	private final SimpMessagingTemplate messagingTemplate;
// 	private final WebSocketContext webSocketContext;

// 	@EventListener
// 	public void handleSubscription(SessionSubscribeEvent event) {
// 		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());


// 		String sessionId = headerAccessor.getSessionId();
// 		String destination = headerAccessor.getDestination();
// 		// messagingTemplate.convertAndSendToUser(sessionId, "/topic/messages", "ok");;

// 		System.out.println("User with session ID: " + sessionId + " subscribed to: " + destination);
// 		// webSocketContext.saveSession(16l, sessionId);
// 	}

// 	// @EventListener
// 	// public void handleWsConnectListener(SessionConnectedEvent event) {
		
// 	// 	StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
// 	// 	// List<String> tokenHeaders = accessor.getNativeHeader("accessToken");
// 	// 	MessageHeaders headerrs = accessor.getMessageHeaders();
// 	// 	// System.out.println(tokenHeaders);
// 	// 	headerrs.values().stream().map(s -> {
// 	// 		System.out.println(s);
// 	// 		return s;});
// 	// 	System.out.println();
// 	// 	System.out.println("PUTTEEEEED");
// 	// }
// }
