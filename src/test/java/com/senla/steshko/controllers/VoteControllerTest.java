package com.senla.steshko.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.steshko.dto.entities.VoteDto;
import com.senla.steshko.dtoapi.VoteDtoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user5@mail.ru")
@TestPropertySource(locations = "classpath:application-test.properties")
public class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VoteDtoService voteDtoService;

    @Test
    public void createTest() throws Exception {

        VoteDto vote = new VoteDto();

        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vote)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void readTest() throws Exception {
        mockMvc.perform(get("/votes/{id}", 0))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void countByCandidateAndEvent() throws Exception {
        mockMvc.perform(get("/votes/countByCandidateAndEvent?c_id={c_id}&e_id={e_id}", 0,0))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        VoteDto vote = new VoteDto();

        mockMvc.perform(put("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vote)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/votes/{id}",0))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
