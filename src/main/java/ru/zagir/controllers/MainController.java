package ru.zagir.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zagir.repositories.ChatRepository;
import ru.zagir.repositories.MessageRepository;
import ru.zagir.repositories.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndex(){
        return "redirect:./chats";
    }
}
