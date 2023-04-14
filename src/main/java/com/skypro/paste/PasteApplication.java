package com.skypro.paste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PasteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasteApplication.class, args);
    }

}
