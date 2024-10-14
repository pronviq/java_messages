package ru.max.messages.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Exceptions extends RuntimeException{
	private final HttpStatus status;
	private final String message;

	public static final Exceptions UNAUTHORIZED = new Exceptions(HttpStatus.UNAUTHORIZED, "Unathorized");
	public static final Exceptions CHAT_ALREADY_EXISTS = new Exceptions(HttpStatus.FORBIDDEN, "Chat already exists");
	public static final Exceptions BAD_DATA = new Exceptions(HttpStatus.FORBIDDEN, "Bad request data");


}
