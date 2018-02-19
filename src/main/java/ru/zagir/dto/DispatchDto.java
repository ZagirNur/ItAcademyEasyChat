package ru.zagir.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatchDto {
    MessageDto message;
    ChatDto chat;
    PartnerDto partner;

}
