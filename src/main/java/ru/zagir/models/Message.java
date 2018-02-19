package ru.zagir.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(exclude = {"author", "chat"})
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String message;

    LocalDateTime time;

    boolean isRead;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User author;

    @ManyToOne()
    @JoinColumn(name = "chat_id")
    Chat chat;
}
