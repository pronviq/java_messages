package ru.max.messages.repositories;

import org.springframework.stereotype.Repository;

import ru.max.messages.models.ChatModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ChatRepository extends JpaRepository<ChatModel, Long> {
	Optional<ChatModel[]> findAllByOwnerIdOrCompanionId(Long owner_id, Long companion_id);	
	Optional<ChatModel> findByOwnerIdAndCompanionUsername(Long owner_id, String companionUsername);
}
