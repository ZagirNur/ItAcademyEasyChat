package ru.zagir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.zagir.repositories.ChatRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.zagir.repositories")
@ComponentScan("ru.zagir")
@EntityScan(basePackages = "ru.zagir.models", basePackageClasses = {Jsr310JpaConverters.class})
public class Applicatinon {


    public static void main(String[] args) {
        SpringApplication.run(Applicatinon.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
