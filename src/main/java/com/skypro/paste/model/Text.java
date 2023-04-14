package com.skypro.paste.model;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
public class Text {
    @Id
    private String key = keyGenerator();
    private String text;
    private String title;
    private String accessType;
    private Instant creationDateTime = Instant.now();
    private Instant expiryDateTime;
    private Boolean isNonDeletable = true;


    private String keyGenerator() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public void setExpiryDateTime(Instant expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
        this.setIsNonDeletable(false);
    }
}
