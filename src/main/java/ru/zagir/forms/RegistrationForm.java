package ru.zagir.forms;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {
    private String login;
    private String password;
    private String confirmPassword;
    private String eMail;
    private String firstName;
    private String lastName;
    private MultipartFile image;
}
