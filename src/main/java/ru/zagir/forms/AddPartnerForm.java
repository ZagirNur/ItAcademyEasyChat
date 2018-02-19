package ru.zagir.forms;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddPartnerForm {
    Long chatId;
    String partnerName;
}
