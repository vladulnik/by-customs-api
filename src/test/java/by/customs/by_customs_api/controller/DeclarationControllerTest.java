package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.service.DeclarationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeclarationController.class)
class DeclarationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeclarationService declarationService;

    private ObjectMapper objectMapper;

    private DeclarationDto testDeclaration;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Для LocalDate

        testDeclaration = new DeclarationDto();
        testDeclaration.setId(1L);
        testDeclaration.setNumber("DEC-2025-001");
        testDeclaration.setDate(LocalDate.of(2025, 6, 10));
    }

    @Test
    void getAllShouldReturnPage() throws Exception {
        Page<DeclarationDto> page = new PageImpl<>(
                Collections.singletonList(testDeclaration),
                PageRequest.of(0, 10),
                1
        );
        given(declarationService.getAll(any(Pageable.class))).willReturn(page);

        mockMvc.perform(get("/api/declarations")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value(1L));
    }

    @Test
    void getByIdShouldReturnDeclaration() throws Exception {
        given(declarationService.getById(1L)).willReturn(testDeclaration);

        mockMvc.perform(get("/api/declarations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.number").value("DEC-2025-001"));
    }

    @Test
    void createShouldReturnOk() throws Exception {
        given(declarationService.create(any(DeclarationDto.class))).willReturn(testDeclaration);

        mockMvc.perform(post("/api/declarations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDeclaration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.number").value("DEC-2025-001"));
    }

    @Test
    void updateShouldReturnOk() throws Exception {
        given(declarationService.update(eq(1L), any(DeclarationDto.class))).willReturn(testDeclaration);

        mockMvc.perform(put("/api/declarations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDeclaration)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.number").value("DEC-2025-001"));
    }

    @Test
    void deleteShouldReturnNoContent() throws Exception {
        doNothing().when(declarationService).delete(1L);
        mockMvc.perform(delete("/api/declarations/1"))
                .andExpect(status().isNoContent());
    }
}
