package ru.max.messages.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.max.messages.models.MessageModel;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long> {
	Optional<MessageModel[]> findAllByChatId(Long chat_id);
}
