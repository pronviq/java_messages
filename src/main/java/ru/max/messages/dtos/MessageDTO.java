package ru.max.messages.dtos;

import lombok.Data;

@Data
public class MessageDTO {
	private Long ownerId;
	private String ownerUsername;
	private String text;
	private Long chatId;
	private String companionUsername;
	private Long companionId;
}