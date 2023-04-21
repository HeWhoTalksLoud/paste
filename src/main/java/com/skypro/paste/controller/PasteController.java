package com.skypro.paste.controller;

import com.skypro.paste.dto.RetrievedTextDTO;
import com.skypro.paste.dto.SearchDTO;
import com.skypro.paste.dto.TextToSaveDTO;
import com.skypro.paste.exception.NotFoundException;
import com.skypro.paste.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class PasteController {
    @Autowired
    private TextService textService;
    @PostMapping("/post")
    ResponseEntity<RetrievedTextDTO> postText(@RequestBody TextToSaveDTO dto) {
        RetrievedTextDTO retrievedTextDTO = textService.save(dto);
        return ResponseEntity.ok(retrievedTextDTO);
    }

    @GetMapping("/get/{key}")
    ResponseEntity<String> getText(@PathVariable(name = "key") String key) {
        RetrievedTextDTO retrievedTextDTO = textService.getByKey(key);
        return ResponseEntity.ok(retrievedTextDTO.getText());
    }

    @GetMapping("/last10")
    ResponseEntity<List<RetrievedTextDTO>> getLast10() {
        return ResponseEntity.ok(textService.getLast10());
    }

    @GetMapping("/search")
    ResponseEntity<List<RetrievedTextDTO>> find(SearchDTO dto) {
        return ResponseEntity.ok(textService.find(dto));
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<String> notFoundHandler(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
