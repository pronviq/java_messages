package ru.max.messages.filters;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ru.max.messages.contexts.WebSocketContext;
import ru.max.messages.service.TokenService;

@RequiredArgsConstructor
public class HandshakeFilter implements HandshakeInterceptor {
	private final TokenService tokenService;
	private final WebSocketContext webSocketContext;

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			@Nullable Exception exception) {
			// WebSocketSession wss = (WebSocketSession) request.get
			
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		ServletServerHttpRequest req = (ServletServerHttpRequest) request;
		HttpServletRequest httpRequest = req.getServletRequest();
		String token = httpRequest.getParameter("accessToken");
		Long user_id;
		try {
			user_id = tokenService.verifyIdFromToken(token);
		} catch (Exception e) {
			return false;
		}
		
		// String sessionId = httpRequest.getSession().getId();
		// System.out.println("\tCONNECTED " + sessionId);
		if (user_id == null)
			return false;

			
		// webSocketContext.saveSession(user_id, sessionId);
		return true;
	}
}
