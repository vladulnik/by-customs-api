package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.service.ParticipantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тесты контроллера Participant
 */
@WebMvcTest(ParticipantController.class)
class ParticipantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipantService participantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Создание Participant — должно вернуть 201")
    void createParticipant_ReturnsCreated() throws Exception {
        ParticipantDto dto = ParticipantDto.builder()
                .id(1L)
                .name("TestName")
                .address("TestAddress")
                .build();

        Mockito.when(participantService.createParticipant(any())).thenReturn(dto);

        mockMvc.perform(post("/api/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(dto.getName())));
    }

    @Test
    @DisplayName("Получение Participant по id — должно вернуть 200")
    void getParticipantById_ReturnsOk() throws Exception {
        ParticipantDto dto = ParticipantDto.builder()
                .id(2L)
                .name("Name2")
                .address("Address2")
                .build();

        Mockito.when(participantService.getParticipantById(2L)).thenReturn(dto);

        mockMvc.perform(get("/api/participants/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(dto.getName())));
    }

    @Test
    @DisplayName("Удаление Participant — должно вернуть 204")
    void deleteParticipant_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/participants/3"))
                .andExpect(status().isNoContent());
        Mockito.verify(participantService).deleteParticipant(3L);
    }
}
