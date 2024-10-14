package ru.max.messages.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.max.messages.contexts.UserContext;
import ru.max.messages.dtos.MessageDTO;
import ru.max.messages.service.MessageService;

@RestController
@RequiredArgsConstructor
public class MessagesController {
	private final MessageService messageService;

	@PostMapping("/sendmessage")
	public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO) {
		System.out.println(messageDTO);
		var user_id = UserContext.getUserId();
		messageDTO.setOwnerId(user_id);
		return messageService.sendMessage(messageDTO); 
	}

	@GetMapping("/getmessages")
	public ResponseEntity<?> getMessages(@RequestParam Long chat_id) {
		// Long user_id = UserContext.getUserId();
		return messageService.getMessages(chat_id);
	}
}
