package com.skypro.paste.model;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Text {
    @Id
    private String key = keyGenerator();
    private String text;
    private String title;
    private String accessType;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime creationDateTime = LocalDateTime.now();
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime expiryDateTime;
    private Boolean isNonDeletable = true;


    private String keyGenerator() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
        this.setIsNonDeletable(false);
    }
}
