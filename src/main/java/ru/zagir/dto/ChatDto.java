package ru.zagir.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.zagir.models.Chat;
import ru.zagir.models.Message;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
public class ChatDto {

    private String id;
    private String chatName;
    private String lastText;

    public static ChatDto from(Chat chat) {
        String lastText = "нет сообщений";
        if (!chat.getMessages().isEmpty()) {
            Message lastMessage = chat.getMessages().get(chat.getMessages().size() - 1);
            lastText = lastMessage.getAuthor().getFirstName() + ": " + lastMessage.getMessage();
        }
        return ChatDto.builder()
                .id(String.valueOf(chat.getId()))
                .chatName(chat.getChatName())
                .lastText(lastText)
                .build();
    }
}
