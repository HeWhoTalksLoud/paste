package com.skypro.paste.dto;

import com.skypro.paste.model.PasteAccessType;
import com.skypro.paste.model.Text;
import com.skypro.paste.util.FormatInstant;
import lombok.Data;

@Data
public class RetrievedTextDTO {
    private String key;
    private String text;
    private String title;
    private PasteAccessType accessType;
    private String creationDateTime;
    private String expiryDateTime;
    public static RetrievedTextDTO fromText(Text text) {
        RetrievedTextDTO dto = new RetrievedTextDTO();
        dto.setKey(text.getKey());
        dto.setText(text.getText());
        dto.setTitle(text.getTitle());
        dto.setAccessType(PasteAccessType.fromString(text.getAccessType()));
        dto.setCreationDateTime(FormatInstant.format(text.getCreationDateTime()));
        if (text.getIsNonDeletable()) {
            dto.setExpiryDateTime(null);
        } else {
            dto.setExpiryDateTime(FormatInstant.format(text.getExpiryDateTime()));
        }
        return dto;
    }
}
