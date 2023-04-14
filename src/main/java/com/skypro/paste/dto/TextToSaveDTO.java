package com.skypro.paste.dto;

import com.skypro.paste.model.AvailabilityTimeUnit;
import com.skypro.paste.model.PasteAccessType;
import com.skypro.paste.model.Text;
import lombok.Data;

import java.time.Instant;


@Data
public class TextToSaveDTO {
    private String text;
    private String title;
    private Integer availabilityTime;
    private AvailabilityTimeUnit availabilityTimeUnit;
    private PasteAccessType accessType;
    private Instant creationDateTime;

    public Text toText() {
        Text text = new Text();
        text.setText(this.getText());
        text.setTitle(this.getTitle());
        text.setAccessType(this.getAccessType().getString());
        if (this.getAvailabilityTime() != null &&
                this.getAvailabilityTime() != 0 ) {
            text.setIsNonDeletable(false);
            text.setExpiryDateTime(text.getCreationDateTime()
                    .plus(getAvailabilityTime(),
                            getAvailabilityTimeUnit().toChronoUnit()));
        }
        return text;
    }
}
