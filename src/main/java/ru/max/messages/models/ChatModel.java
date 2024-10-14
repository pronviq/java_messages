package ru.max.messages.models;

import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "chats")
public class ChatModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 16)
	private String companionUsername;
	@Column(columnDefinition = "DEFAULT 'FALSE'")
	private boolean isRead;
	@Column(nullable = false)
	private Long ownerId;
	@Column(nullable = false)
	private Long companionId;
	@Column(nullable = false)
	private String ownerUsername;
}
