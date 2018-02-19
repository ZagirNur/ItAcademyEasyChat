package ru.zagir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zagir.models.Message;
import ru.zagir.models.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByChat_IdAndAuthor_IdOrderByTime(Long chatId,Long authorId);
}
