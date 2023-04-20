package com.skypro.paste.service;

import com.skypro.paste.dto.SearchDTO;
import com.skypro.paste.exception.NotFoundException;
import com.skypro.paste.model.Text;
import com.skypro.paste.repository.TextRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TextServiceTest {

    @Mock
    TextRepository textRepository;

    @InjectMocks
    TextService textService;

    @Test
    void save() {
        assertThrows(NullPointerException.class, () -> textService.save(null));
    }

    @Test
    void getByKey() {
        assertThrows(NotFoundException.class, () -> textService.getByKey("00"));
    }

    @Test
    void getLast10() {
        Text text = new Text();
        text.setKey("11");
        text.setText("11");
        text.setTitle("11");
        text.setAccessType("PUBLIC");

        when(textRepository.findLast10(any(LocalDateTime.class))).thenReturn(List.of(text, text));
        assertDoesNotThrow(() -> textService.getLast10());
    }

    @Test
    void find() {
        when(textRepository.findAll(any(Specification.class))).thenReturn(new ArrayList<>());

        SearchDTO dto = new SearchDTO();
        dto.setText("11");
        dto.setTitle("11");
        assertThrows(NotFoundException.class, () -> textService.find(dto));
    }
}