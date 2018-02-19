package ru.zagir.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.zagir.forms.RegistrationForm;
import ru.zagir.models.User;
import ru.zagir.repositories.UserRepository;

import java.util.Optional;

@Component
public class SignUpValidator implements Validator {

    @Autowired
    UserRepository userRepository;

    @Override
    public void validate(Object obj, Errors errors) {
        RegistrationForm signUpForm = (RegistrationForm) obj;
        Optional<User> existedUser = userRepository.findUserByLogin(signUpForm.getLogin());

        if (existedUser.isPresent()){
            errors.rejectValue("login",  "login.error","Пользователь с таким именем уже зарегистрирован");
        }

        if (!signUpForm.getLogin().matches("[a-zA-Z0-9.]{4,}")) {
            errors.rejectValue("login",  "","Имя пользователя должно быть длиннее 4 символов и состоять из цифр, букв английского алфавита и точек.");
        }

        if (!signUpForm.getPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
            errors.rejectValue("password",  "","Пароль недостаточно сложен: должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов.");
        }

        if (!(signUpForm.getPassword()).equals(signUpForm.getConfirmPassword())) {
            errors.rejectValue("confirmPassword",  "","Пароль и повтор пароля не совпадают.");
        }
        System.out.println("validation");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(RegistrationForm.class.getName());
    }
}
