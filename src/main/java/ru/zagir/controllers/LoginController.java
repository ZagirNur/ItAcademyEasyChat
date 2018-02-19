package ru.zagir.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/sign-in")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           ModelMap model){
        if (error != null) {
            model.addAttribute("error", "Имя пользователя и пароль не подходят");
        }
        return "login";
    }
}
