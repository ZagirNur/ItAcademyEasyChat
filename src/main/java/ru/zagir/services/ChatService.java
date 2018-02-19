package ru.zagir.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.zagir.dto.*;
import ru.zagir.forms.AddChatForm;
import ru.zagir.forms.AddPartnerForm;
import ru.zagir.forms.SendMessageForm;
import ru.zagir.models.Chat;
import ru.zagir.models.Message;
import ru.zagir.models.User;
import ru.zagir.repositories.ChatRepository;
import ru.zagir.repositories.MessageRepository;
import ru.zagir.repositories.UserRepository;
import ru.zagir.security.detail.UserDetailImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    SimpMessageSendingOperations simpMessageSendingOperations;

    public User getUser(Authentication authentication) {
        return userRepository.findOne(
                ((UserDetailImpl) authentication.getPrincipal()).getId());
    }

    public Message saveMessageIfOk(SendMessageForm form, Authentication authentication) {
        User author = getUser(authentication);
        Chat chat = chatRepository.findFirstByIdAndUsersContaining(form.getChatId(), author);
        chat.setChangeTime(LocalDateTime.now());
        Message message = Message.builder()
                .message(form.getMessage())
                .chat(chat)
                .author(author)
                .time(chat.getChangeTime())
                .isRead(false)
                .build();
        chatRepository.save(chat);
        messageRepository.save(message);
        return message;
    }

    public void sendMessageIfOk(SendMessageForm form, Authentication authentication) {
        Message message = saveMessageIfOk(form, authentication);
        message.getChat().getUsers()
                .forEach(user -> simpMessageSendingOperations
                        .convertAndSendToUser(user.getLogin(), "/subscribe",
                                DispatchDto.builder()
                                        .message(MessageDto.from(message))
                                        .build()));


    }

    public void addChat(AddChatForm form, Authentication authentication) {
        List<User> users = Collections.singletonList(getUser(authentication));
        Chat chat = Chat.builder()
                .chatName(form.getChatName())
                .messages(new ArrayList<>())
                .changeTime(LocalDateTime.now())
                .users(users)
                .build();
        chatRepository.save(chat);
        simpMessageSendingOperations.convertAndSendToUser(
                authentication.getName(),
                "/subscribe",
                DispatchDto.builder().chat(ChatDto.from(chat)).build());
    }

    public void addPartner(AddPartnerForm form, Authentication authentication) {
        Chat chat = chatRepository
                .findFirstByIdAndUsersContaining(form.getChatId(), getUser(authentication));
        User user = userRepository.findUserByLogin(
                form.getPartnerName()).orElseThrow(() ->
                new UsernameNotFoundException("Пользователь не найден"));
        chat.getUsers().add(user);
        chatRepository.save(chat);
        chat.getUsers().forEach(u -> simpMessageSendingOperations
                .convertAndSendToUser(
                        u.getLogin(),
                        "/subscribe",
                        DispatchDto.builder()
                                .partner(PartnerDto.builder()
                                        .chatId(String.valueOf(chat.getId()))
                                        .fullName(user.getFirstName() + " " + user.getLastName())
                                        .build())
                                .build()));

    }

    public GeneralDto getFullInfo(Authentication authentication) {
        return GeneralDto.fromChatList(chatRepository.findAllByUsersContainingOrderByChangeTimeDesc(getUser(authentication)));
    }

    public GeneralDto getCurrentChat(Authentication authentication, Long chatId) {
        return GeneralDto.fromChat(chatRepository.findFirstByIdAndUsersContaining(chatId, getUser(authentication)));
    }

    public void deleteChat(Long id) {
        chatRepository.delete(id);
    }
}
