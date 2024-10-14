package ru.max.messages.mappers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import ru.max.messages.dtos.MessageDTO;
import ru.max.messages.models.MessageModel;

@Component
public class MessageMapper {
	public MessageModel dtoToModel(MessageDTO messageDTO) {
		var messageModel = new MessageModel();
		messageModel.setChatId(messageDTO.getChatId());
		messageModel.setOwnerId(messageDTO.getOwnerId());
		messageModel.setOwnerUsername(messageDTO.getOwnerUsername());
		messageModel.setDispatchDate(LocalDateTime.now());
		messageModel.setText(messageDTO.getText());
		messageModel.setCompanionId(messageDTO.getCompanionId());
		messageModel.setCompanionUsername(messageDTO.getCompanionUsername());
		return messageModel;
	}
}
