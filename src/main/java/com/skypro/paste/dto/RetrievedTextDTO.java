package com.skypro.paste.dto;

import com.skypro.paste.model.PasteAccessType;
import com.skypro.paste.model.Text;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class RetrievedTextDTO {
    private String key;
    private String text;
    private String title;
    private PasteAccessType accessType;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime creationDateTime;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime expiryDateTime;
    public static RetrievedTextDTO fromText(Text text) {
        RetrievedTextDTO dto = new RetrievedTextDTO();
        dto.setKey(text.getKey());
        dto.setText(text.getText());
        dto.setTitle(text.getTitle());
        dto.setAccessType(PasteAccessType.fromString(text.getAccessType()));
        dto.setCreationDateTime(text.getCreationDateTime());
        if (text.getIsNonDeletable()) {
            dto.setExpiryDateTime(null);
        } else {
            dto.setExpiryDateTime(text.getExpiryDateTime());
        }
        return dto;
    }
}
