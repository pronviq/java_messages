package ru.max.messages.contexts;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class WebSocketContext {
	private final Map<Long, String> sessions = new ConcurrentHashMap<>();

	public void saveSession(Long user_id, String sessionId) {
		sessions.put(user_id, sessionId);
	}

	public String getSessionId(Long user_id) {
		return sessions.get(user_id);
	}

	public void removeSession(Long user_id) {
		sessions.remove(user_id);
	}
}
