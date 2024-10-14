package ru.max.messages.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.max.messages.contexts.UserContext;
import ru.max.messages.dtos.ChatDTO;
import ru.max.messages.exceptions.Exceptions;
import ru.max.messages.models.ChatModel;
import ru.max.messages.repositories.ChatRepository;

@Service
@RequiredArgsConstructor
public class ChatService {
	private final ChatRepository chatRepository;

	public ResponseEntity<ChatModel[]> getChats() {
		Long user_id = UserContext.getUserId();
		Optional<ChatModel[]> tryChats =  chatRepository.findAllByOwnerIdOrCompanionId(user_id, user_id);
		ChatModel[] chats = tryChats.get();
		return ResponseEntity.ok(chats);
	}

	public ResponseEntity<?> createChat(ChatDTO chatDTO) {
		Long owner_id = UserContext.getUserId();
		ChatModel tryChat = chatRepository.findByOwnerIdAndCompanionUsername(owner_id, chatDTO.getCompanionUsername()).orElse(null);
		// System.out.println("creating " + chatDTO);
		if (tryChat != null || owner_id == chatDTO.getCompanionId())
			throw Exceptions.CHAT_ALREADY_EXISTS;

		ChatModel	chatModel = new ChatModel();
		chatModel.setOwnerUsername(chatDTO.getOwnerUsername());
		chatModel.setCompanionId(chatDTO.getCompanionId());
		chatModel.setCompanionUsername(chatDTO.getCompanionUsername());
		chatModel.setOwnerId(owner_id);
		chatModel.setRead(true);
		ChatModel respModel = chatRepository.save(chatModel);
		return ResponseEntity.ok(respModel);
	}

	public ResponseEntity<?> deleteChat(Long chat_id) {
		System.out.println(chat_id);
		if (chat_id == null) 
			throw Exceptions.BAD_DATA;

		chatRepository.deleteById(chat_id);
		return ResponseEntity.ok(null);
	}
}
