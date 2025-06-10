package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.exception.GlobalExceptionHandler;
import by.customs.by_customs_api.service.ParticipantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.data.domain.PageImpl.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ParticipantControllerTest {

    private MockMvc mockMvc;
    private ParticipantService service;
    private ObjectMapper objectMapper;
    private ParticipantDto testParticipant;

    @BeforeEach
    public void setUp() {
        service = Mockito.mock(ParticipantService.class);
        ParticipantController controller = new ParticipantController(service);

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        testParticipant = new ParticipantDto();
        testParticipant.setId(1L);
        testParticipant.setName("Тестовый участник");
        testParticipant.setAddress("Минск, ул. Ленина, 10");
        testParticipant.setDeclarationId(1L);
    }

    @Test
    public void getAllShouldReturnPage() throws Exception {
        Page<ParticipantDto> page = new PageImpl<>(
                singletonList(testParticipant),
                PageRequest.of(0, 10),
                1
        );
        given(service.getAll(any())).willReturn(page);

        mockMvc.perform(get("/api/participants")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value(1));
    }

    @Test
    public void getByIdShouldReturnParticipant() throws Exception {
        given(service.getById(1L)).willReturn(testParticipant);

        mockMvc.perform(get("/api/participants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Тестовый участник"))
                .andExpect(jsonPath("$.address").value("Минск, ул. Ленина, 10"));
    }

    @Test
    public void createShouldReturnOk() throws Exception {
        given(service.create(any(ParticipantDto.class))).willReturn(testParticipant);

        mockMvc.perform(post("/api/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testParticipant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.declarationId").value(1));
    }

    @Test
    public void updateShouldReturnOk() throws Exception {
        given(service.update(eq(1L), any(ParticipantDto.class))).willReturn(testParticipant);

        mockMvc.perform(put("/api/participants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testParticipant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Тестовый участник"));
    }

    @Test
    public void deleteShouldReturnNoContent() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/participants/1"))
                .andExpect(status().isNoContent());
    }
}
