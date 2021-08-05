package learn.recordcollection.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.dynalink.linker.LinkerServices;
import learn.recordcollection.data.RecordRepository;
import learn.recordcollection.domain.Result;
import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RecordControllerTest {

    @MockBean
    RecordRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldFindAll() throws Exception {

        List<Record> all = Arrays.asList(
                new Record(1, "Prince", "Sign `O` the Times", Condition.NEAR_MINT, 45),
                new Record(2, "Bob Dylan", "Blood on the Tracks", Condition.GOOD, 15),
                new Record(3, "Madvillain", "Madvillainy", Condition.NEAR_MINT, 30),
                new Record(4, "Prince", "1999", Condition.VERY_GOOD, 23)
        );

        when(repository.findAll()).thenReturn(all);

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(all);

        mvc.perform(get("/records"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldAdd() throws Exception {
        Record recordIn = new Record(0, "Test 1", "Test 2", Condition.GOOD, 1);
        Record recordOut = new Record(1, "Test 1", "Test 2", Condition.GOOD, 1);

        when(repository.add(any())).thenReturn(recordOut);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(recordIn);
        String jsonOut = jsonMapper.writeValueAsString(recordOut);

        var request = post("/records")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonOut));
    }
}