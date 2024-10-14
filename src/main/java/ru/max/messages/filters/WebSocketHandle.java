// package ru.max.messages.filters;

// import org.springframework.http.server.ServerHttpRequest;
// import org.springframework.lang.Nullable;
// import org.springframework.stereotype.Component;
// import org.springframework.web.socket.WebSocketHandler;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

// import java.security.Principal;
// import java.util.Map;


// @Component
// public class WebSocketHandle extends DefaultHandshakeHandler {

// 	@Override
// 	@Nullable
// 	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
// 			Map<String, Object> attributes) {
// 		// TODO Auto-generated method stub
// 		String userId = "hello";

// 		System.out.println("handling");
// 		return new Principal() {
// 			@Override
// 			public String getName() {
// 				return userId;
// 			}
// 	};
// 		// return super.determineUser(request, wsHandler, attributes);
// 	}
	
// }
