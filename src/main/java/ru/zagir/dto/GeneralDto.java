package ru.zagir.dto;

import lombok.Builder;
import lombok.Data;
import ru.zagir.models.Chat;
import ru.zagir.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class GeneralDto {
    private String currentChatLabel;
    private List<ChatDto> chats;
    private List<MessageDto> currentChatMessages;
    private List<PartnerDto> currentChatPartners;

    public static GeneralDto fromUser(User user) {
        GeneralDto generalDto = GeneralDto.builder()
                .chats(user.getChats().stream()
                        .map(ChatDto::from)
                        .collect(Collectors.toList()))
                .build();
        if (!user.getChats().isEmpty()){
            Chat chat = user.getChats().get(user.getChats().size()-1);
            generalDto.setCurrentChatMessages(chat.getMessages().stream()
                    .map(MessageDto::from)
                    .collect(Collectors.toList()));
            generalDto.setCurrentChatPartners(chat.getUsers().stream()
                    .map(PartnerDto::from)
                    .collect(Collectors.toList()));
            generalDto.currentChatLabel = chat.getChatName();
        }
        return generalDto;

    }

    public static GeneralDto fromChatList(List<Chat> chats){
        return GeneralDto.builder()
                .chats(chats.stream().map(ChatDto::from).collect(Collectors.toList()))
                .currentChatLabel(chats.get(0).getChatName())
                .currentChatPartners(chats.get(0).getUsers().stream().map(PartnerDto::from).collect(Collectors.toList()))
                .currentChatMessages(chats.get(0).getMessages().stream().map(MessageDto::from).collect(Collectors.toList()))
                .build();
    }

    public static GeneralDto fromChat(Chat chat) {
        return GeneralDto.builder()
                .currentChatLabel(chat.getChatName())
                .currentChatMessages(chat.getMessages().stream()
                        .map(MessageDto::from)
                        .collect(Collectors.toList()))
                .currentChatPartners(chat.getUsers().stream()
                        .map(PartnerDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
