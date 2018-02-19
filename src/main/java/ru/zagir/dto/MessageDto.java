package ru.zagir.dto;


import lombok.*;
import ru.zagir.models.FileInfo;
import ru.zagir.models.Message;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Function;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    String chatId;
    String imageUrl;
    String firstName;
    String lastName;
    String text;
    String time;

    public static MessageDto from(Message message){
        return MessageDto.builder()
                .chatId(String.valueOf(message.getChat().getId()))
                .imageUrl(
                        Optional.ofNullable(
                                message
                        .getAuthor()
                        .getImage())
                        .map(fileInfo -> "/files/" + fileInfo.getStorageFileName())
                        .orElse(""))
                .firstName(message.getAuthor().getFirstName())
                .lastName(message.getAuthor().getLastName())
                .text(message.getMessage())
                .time(message.getTime().format(DateTimeFormatter.ofPattern("hh:mm")))
                .build();
    }
}
