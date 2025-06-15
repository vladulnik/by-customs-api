package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.service.DeclarationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты контроллера деклараций
 */
@WebMvcTest(DeclarationController.class)
class DeclarationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeclarationService declarationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Создание декларации — должно вернуть 201")
    void createDeclaration_ReturnsCreated() throws Exception {
        DeclarationDto dto = DeclarationDto.builder()
                .id(1L)
                .number("DEC-1")
                .date(LocalDate.now())
                .build();

        Mockito.when(declarationService.createDeclaration(any())).thenReturn(dto);

        mockMvc.perform(post("/api/declarations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.number", is(dto.getNumber())));
    }

    @Test
    @DisplayName("Получение декларации по id — должно вернуть 200")
    void getDeclarationById_ReturnsOk() throws Exception {
        DeclarationDto dto = DeclarationDto.builder()
                .id(2L)
                .number("DEC-2")
                .date(LocalDate.now())
                .build();

        Mockito.when(declarationService.getDeclarationById(2L)).thenReturn(dto);

        mockMvc.perform(get("/api/declarations/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.number", is(dto.getNumber())));
    }

    @Test
    @DisplayName("Получение списка деклараций — должно вернуть Page")
    void getAllDeclarations_ReturnsPage() throws Exception {
        DeclarationDto dto = DeclarationDto.builder()
                .id(3L)
                .number("DEC-3")
                .date(LocalDate.now())
                .build();

        Mockito.when(declarationService.getAllDeclarations(ArgumentMatchers.<Pageable>any()))
                .thenReturn(new PageImpl<>(Collections.singletonList(dto)));

        mockMvc.perform(get("/api/declarations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].number", is(dto.getNumber())));
    }

    @Test
    @DisplayName("Обновление декларации — должно вернуть 200")
    void updateDeclaration_ReturnsOk() throws Exception {
        DeclarationDto dto = DeclarationDto.builder()
                .id(4L)
                .number("DEC-4")
                .date(LocalDate.now())
                .build();

        Mockito.when(declarationService.updateDeclaration(eq(4L), any())).thenReturn(dto);

        mockMvc.perform(put("/api/declarations/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.number", is(dto.getNumber())));
    }

    @Test
    @DisplayName("Удаление декларации — должно вернуть 204")
    void deleteDeclaration_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/declarations/5"))
                .andExpect(status().isNoContent());
        Mockito.verify(declarationService).deleteDeclaration(5L);
    }
}
