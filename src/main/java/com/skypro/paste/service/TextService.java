package com.skypro.paste.service;

import com.skypro.paste.dto.RetrievedTextDTO;
import com.skypro.paste.dto.SearchDTO;
import com.skypro.paste.dto.TextToSaveDTO;
import com.skypro.paste.exception.NotFoundException;
import com.skypro.paste.model.PasteAccessType;
import com.skypro.paste.model.Text;
import com.skypro.paste.repository.TextRepository;
import com.skypro.paste.repository.specification.TextSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextService {
    @Autowired
    private TextRepository textRepository;

    public RetrievedTextDTO save(TextToSaveDTO dto) {
        Text text = dto.toText();
        return RetrievedTextDTO.fromText(textRepository.save(text));

    }

    public RetrievedTextDTO getByKey(String key) {
        Text text = textRepository.findById(key).orElseThrow(() -> new NotFoundException("Текст с ключом " +
                key + " не найден"));
        return RetrievedTextDTO.fromText(text);
    }

    public List<RetrievedTextDTO> getLast10() {
        List<Text> texts = textRepository.findLast10(PasteAccessType.PUBLIC.getString());
        if (texts.isEmpty()) {
            throw new NotFoundException("Не удалось найти 10 последних публичных текстов");
        }
        return texts.stream()
                .map(RetrievedTextDTO::fromText)
                .collect(Collectors.toList());
    }

    public List<RetrievedTextDTO> find(SearchDTO dto) {
        String title = dto.getTitle();
        String text = dto.getText();

        Specification<Text> textSpecification = (root, query, cb) -> cb.conjunction();
        textSpecification = textSpecification.and(TextSpecification.byTitle(title));
        textSpecification = textSpecification.and(TextSpecification.byText(text));
        textSpecification = textSpecification.and(TextSpecification.publicOnly());

        List<Text> texts = textRepository.findAll(textSpecification);

        if (texts.isEmpty()) {
            throw new NotFoundException("Не найдены тексты по следующим условиям:\n" +
                    "Заголовок: " + title + "\n" +
                    "Текст: " + text);
        }

        return texts.stream()
                .map(RetrievedTextDTO::fromText)
                .collect(Collectors.toList());
    }
}
