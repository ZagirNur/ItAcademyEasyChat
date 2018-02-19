package ru.zagir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.zagir.models.Chat;
import ru.zagir.models.User;

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Chat findFirstByIdAndUsersContaining(Long id, User user);
    @Query(nativeQuery = true, value =
            "select c.id, c.chat_name from chat c inner join user_chat uc on c.id = uc.chat_id left join message m on c.id = m.chat_id where uc.user_id = ? group by c.id order by max (m.time)")
    List<Chat> myQuery(Long id);
    List<Chat> findAllByUsersContainingOrderByChangeTimeDesc(User user);
}
