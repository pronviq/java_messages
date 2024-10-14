package ru.max.messages.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import ru.max.messages.contexts.UserContext;
import ru.max.messages.contexts.WebSocketContext;
import ru.max.messages.dtos.MessageDTO;
import ru.max.messages.mappers.MessageMapper;
import ru.max.messages.models.MessageModel;
import ru.max.messages.repositories.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final MessageRepository messageRepository;
	private final MessageMapper messageMapper;
	private final WebSocketContext webSocketContext;
	private final SimpMessagingTemplate messagingTemplate;
	private final ObjectMapper objectMapper;

	public ResponseEntity<?> sendMessage(MessageDTO messageDTO) {
		// System.out.println("SENDING MESSAGE");
		MessageModel messageModel = messageMapper.dtoToModel(messageDTO);

		// String sessionId = webSocketContext.getSessionId(messageDTO.getOwnerId());
		try {
			// messagingTemplate.convertAndSendToUser("hello", "/topic/messages", "hello bro");
			
			messagingTemplate.convertAndSend("/topic/messages", objectMapper.writeValueAsString(messageModel));
			// System.out.println("converted " + objectMapper.writeValueAsString(messageModel));
			messageRepository.save(messageModel);
			
		} catch (Exception e) {
			System.out.println("error " + e);
			return ResponseEntity.status(500).body("Internal Server Error");
		}
		return ResponseEntity.ok(messageModel);
	}

	public ResponseEntity<?> getMessages(Long chat_id) {
		// провалидировать чат юзера то что это действительно его чат
		Optional<MessageModel[]> messages = messageRepository.findAllByChatId(chat_id);
		return ResponseEntity.ok(messages);
	}	
}
