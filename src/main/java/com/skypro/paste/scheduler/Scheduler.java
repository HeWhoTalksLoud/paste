package com.skypro.paste.scheduler;

import com.skypro.paste.model.Text;
import com.skypro.paste.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Scheduler {

    @Autowired
    private TextRepository textRepository;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    void scheduledEvent() {
        List<Text> obsoleteRecords = textRepository.findObsoleteRecords(Instant.now());
        if (!obsoleteRecords.isEmpty()) {
            textRepository.deleteAll(obsoleteRecords);
        }
    }
}
