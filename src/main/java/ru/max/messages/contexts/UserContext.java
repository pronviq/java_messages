package ru.max.messages.contexts;

public class UserContext {
	private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

	public static void setUserId(Long user_id) {
		threadLocal.set(user_id);
	}

	public static Long getUserId() {
		return threadLocal.get();
	}

	public static void clear() {
		threadLocal.remove();
	}
}
