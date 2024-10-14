package ru.max.messages.dtos;

import lombok.Data;

@Data
public class ChatDTO {
	private String companionUsername;
	private Long companionId;
	private String ownerUsername;
}
