package ru.zagir.controllers;

import lombok.SneakyThrows;
import org.omg.CosNaming.BindingHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.zagir.forms.RegistrationForm;
import ru.zagir.services.RegistrationService;
import ru.zagir.validators.SignUpValidator;

import javax.validation.Valid;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.BindingType;
import java.net.BindException;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    SignUpValidator validator;

    @InitBinder("registrationForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("/sign-up")
    public String getRegPage() {
        return "registration";
    }

    @PostMapping(value = "/sign-up")
    public String registration(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
                               BindingResult error,
                               ModelMap model) {
        if (error.hasErrors()) {
            model.addAttribute("error", error.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("login", registrationForm.getLogin());
            return "registration";
        }
        registrationService.registration(registrationForm);
        return "redirect:./";
    }
}
