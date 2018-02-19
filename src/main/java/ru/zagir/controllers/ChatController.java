package ru.zagir.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.zagir.dto.ChatDto;
import ru.zagir.dto.GeneralDto;
import ru.zagir.forms.AddChatForm;
import ru.zagir.forms.AddPartnerForm;
import ru.zagir.forms.SendMessageForm;
import ru.zagir.services.ChatService;

@Controller
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping("/chats")
    public String getChats(ModelMap model, Authentication authentication) {
        model.addAttribute("me", chatService.getUser(authentication));
        return "chat_page";
    }

    @GetMapping("/get_full_info")
    @ResponseBody
    public GeneralDto getFullInfo(Authentication authentication){
        return chatService.getFullInfo(authentication);
    }

    @GetMapping("/get_messages")
    @ResponseBody
    public GeneralDto getMessages(@RequestParam("chatId") Long chatId,
                               Authentication authentication) {
        return chatService.getCurrentChat(authentication, chatId);
    }

    @PostMapping("/add_chat")
    @ResponseBody
    public void chatAdd(@ModelAttribute AddChatForm form,
                        Authentication authentication) {
        chatService.addChat(form, authentication);

    }

    @PostMapping("/add_partner")
    @ResponseBody
    public void partnerAdd(@ModelAttribute AddPartnerForm form,
                        Authentication authentication) {
        chatService.addPartner(form, authentication);

    }

    @PostMapping("/send_message")
    @ResponseBody
    public void messageSend(@ModelAttribute SendMessageForm form,
                            Authentication authentication) {
        chatService.sendMessageIfOk(form, authentication);

    }

    @PostMapping("/delete_chat")
    @ResponseBody
    public void deleteChat(@RequestParam Long id){
        chatService.deleteChat(id);
    }
}
