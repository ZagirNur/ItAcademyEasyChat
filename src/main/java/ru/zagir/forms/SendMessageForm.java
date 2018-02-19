package ru.zagir.forms;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SendMessageForm {
    Long chatId;
    String message;
}
