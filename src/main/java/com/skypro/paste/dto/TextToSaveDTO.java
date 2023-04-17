package com.skypro.paste.dto;

import com.skypro.paste.model.PasteAccessType;
import com.skypro.paste.model.Text;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Data
public class TextToSaveDTO {
    private String text;
    private String title;
    private ExpirationPeriod expirationPeriod;
    private PasteAccessType accessType;
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime creationDateTime;

    public Text toText() {
        Text text = new Text();
        text.setText(this.getText());
        text.setTitle(this.getTitle());
        text.setAccessType(this.getAccessType().getString());
        if (this.expirationPeriod != ExpirationPeriod.UNLIMITED) {
            text.setIsNonDeletable(false);
            text.setExpiryDateTime(calculateExpTime(this.getCreationDateTime(), expirationPeriod));
        }
        return text;
    }

    private LocalDateTime calculateExpTime(LocalDateTime startDateTime, ExpirationPeriod expPeriod) {
        LocalDateTime expDateTime = startDateTime;
        switch (expPeriod) {
            case TEN_MINUTES:
                expDateTime = startDateTime.plus(10, ChronoUnit.MINUTES);
                break;
            case ONE_HOUR:
                expDateTime = startDateTime.plus(1, ChronoUnit.HOURS);
                break;
            case THREE_HOURS:
                expDateTime = startDateTime.plus(3, ChronoUnit.HOURS);
                break;
            case ONE_DAY:
                expDateTime = startDateTime.plus(1, ChronoUnit.DAYS);
                break;
            case ONE_WEEK:
                expDateTime = startDateTime.plus(1, ChronoUnit.WEEKS);
                break;
            case ONE_MONTH:
                expDateTime = startDateTime.plus(1, ChronoUnit.MONTHS);
                break;
        }
        return expDateTime;
    }
}
