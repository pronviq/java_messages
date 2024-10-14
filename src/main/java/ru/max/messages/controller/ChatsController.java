package ru.max.messages.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.max.messages.contexts.UserContext;
import ru.max.messages.dtos.ChatDTO;
import ru.max.messages.models.ChatModel;
import ru.max.messages.repositories.ChatRepository;
import ru.max.messages.service.ChatService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class ChatsController {
	// private final ChatRepository chatRepository;
	// private final ChatRepository chatRepository;
	private final ChatService chatService;

	@GetMapping("/ping")
	public String ping() {
		return "pong from chats controller :) your id is " + UserContext.getUserId();
	}	

	@GetMapping("/getchats")
	public ResponseEntity<ChatModel[]> getChats() {
		return chatService.getChats();
	}

	@PostMapping("/createchat")
	public ResponseEntity<?> createChat(@RequestBody ChatDTO chatDTO) {
		return chatService.createChat(chatDTO);
	}

	@DeleteMapping("/deletechat")
	public ResponseEntity<?> deleteChat(@RequestParam Long chat_id) {
		return chatService.deleteChat(chat_id);
	}

	
}
