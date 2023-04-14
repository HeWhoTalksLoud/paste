package com.skypro.paste.controller;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PasteControllerTest {
    @Autowired
    MockMvc mockMvc;

    private final List<JSONObject> jsonObjects = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception {
        for (int i = 0; i < 5; i++) {
            JSONObject jsonText = new JSONObject();
            jsonText.put("text", "text" + i);
            jsonText.put("title", "title" + i);
            jsonText.put("availabilityTime", 0);
            jsonText.put("availabilityTimeUnit", "MINUTES");
            jsonText.put("accessType", "PUBLIC");
            jsonText.put("creationDateTime", Instant.now().toString());
            jsonObjects.add(jsonText);
        }


        for (JSONObject jsonObject : jsonObjects) {
            mockMvc.perform(post("/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonObject.toString()))
                    .andExpect(status().isOk()).andReturn();
        }

    }

    @AfterEach
    void tearDown() {
        jsonObjects.clear();
    }

    @Test
    void postText() throws Exception {
        JSONObject jsonText = new JSONObject();
        jsonText.put("text", "test");
        jsonText.put("title", "test");
        jsonText.put("availabilityTime", 0);
        jsonText.put("availabilityTimeUnit", "MINUTES");
        jsonText.put("accessType", "PUBLIC");
        jsonText.put("creationDateTime", Instant.now().toString());

        mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonText.toString()))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void getText() throws Exception {
        JSONObject jsonText = new JSONObject();
        jsonText.put("text", "get text test");
        jsonText.put("title", "test");
        jsonText.put("availabilityTime", 0);
        jsonText.put("availabilityTimeUnit", "MINUTES");
        jsonText.put("accessType", "PUBLIC");
        jsonText.put("creationDateTime", Instant.now().toString());

        MvcResult mvcResult = mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonText.toString()))
                        .andReturn();
        JSONParser parser = new JSONParser(mvcResult.getResponse().getContentAsString());
        String key = parser.object().get("key").toString();
        mvcResult = mockMvc.perform(get("/get/" + key))
                .andExpect(status().isOk()).andReturn();
        String text = mvcResult.getResponse().getContentAsString();
        assertEquals(text, "get text test");

    }

    @Test
    void getLast10() throws Exception {
        mockMvc.perform(get("/last10"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void find() throws Exception {
        JSONObject jsonText = new JSONObject();
        jsonText.put("text", "get_text_test");
        jsonText.put("title", "test_search");
        jsonText.put("availabilityTime", 0);
        jsonText.put("availabilityTimeUnit", "MINUTES");
        jsonText.put("accessType", "PUBLIC");
        jsonText.put("creationDateTime", Instant.now().toString());

        mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonText.toString()))
                        .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/search?title=test_search"))
                .andExpect(status().isOk())
                .andReturn();
        String text = mvcResult.getResponse().getContentAsString();
        assertTrue(text.contains("get_text_test"));
        mvcResult = mockMvc.perform(get("/search?text=get_text_test"))
                .andExpect(status().isOk())
                .andReturn();
        text = mvcResult.getResponse().getContentAsString();
        assertTrue(text.contains("test_search"));
    }

}