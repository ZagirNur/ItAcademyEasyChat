package ru.zagir.dto;

import lombok.Builder;
import lombok.Data;
import ru.zagir.models.Chat;
import ru.zagir.models.User;

@Data
@Builder
public class PartnerDto {
    private String chatId;
    private String fullName;

    public static PartnerDto from(User user){
        return PartnerDto.builder()
                .fullName(user.getFirstName()+" "+user.getLastName()).build();
    }
}
