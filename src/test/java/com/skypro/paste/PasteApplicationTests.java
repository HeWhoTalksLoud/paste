package com.skypro.paste;

import com.skypro.paste.model.PasteAccessType;
import com.skypro.paste.model.Text;
import com.skypro.paste.repository.TextRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

@SpringBootTest
class PasteApplicationTests {

    @Autowired
    private TextRepository textRepository;

    @Test
    void creationTestExpiringInOneMinute() {
        for (int i = 0; i < 5; i++) {
            Text text = new Text();
            text.setText(UUID.randomUUID().toString());
            text.setTitle("lolol");
            text.setAccessType(PasteAccessType.PUBLIC.getString());
            text.setExpiryDateTime(text.getCreationDateTime().plus(1,
                    ChronoUnit.MINUTES));
            textRepository.save(text);
        }

    }

    @Test
    void creationTestExpiringInThirtyMinutes() {
        for (int i = 0; i < 5; i++) {
            Text text = new Text();
            text.setText(UUID.randomUUID().toString());
            text.setTitle("lolol");
            text.setAccessType(PasteAccessType.PUBLIC.getString());
            text.setExpiryDateTime(text.getCreationDateTime().plus(30,
                    ChronoUnit.MINUTES));
            textRepository.save(text);
        }

    }



}
