package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тесты контроллера Item
 */
@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Создание Item — должно вернуть 201")
    void createItem_ReturnsCreated() throws Exception {
        ItemDto dto = ItemDto.builder()
                .id(1L)
                .hsCode("1234")
                .value(100.0)
                .weight(10.0)
                .originCountry("BY")
                .declarationId(42L)
                .build();

        Mockito.when(itemService.createItem(any())).thenReturn(dto);

        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.hsCode", is(dto.getHsCode())));
    }

    @Test
    @DisplayName("Получение Item по id — должно вернуть 200")
    void getItemById_ReturnsOk() throws Exception {
        ItemDto dto = ItemDto.builder()
                .id(2L)
                .hsCode("4321")
                .value(50.0)
                .weight(5.0)
                .originCountry("RU")
                .build();

        Mockito.when(itemService.getItemById(2L)).thenReturn(dto);

        mockMvc.perform(get("/api/items/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.hsCode", is(dto.getHsCode())));
    }

    @Test
    @DisplayName("Удаление Item — должно вернуть 204")
    void deleteItem_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/items/3"))
                .andExpect(status().isNoContent());
        Mockito.verify(itemService).deleteItem(3L);
    }

    @Test
    void getItem_NotFound_Returns404() throws Exception {
        Mockito.when(itemService.getItemById(999L))
                .thenThrow(new by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException("Item not found"));
        mockMvc.perform(get("/api/items/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createItem_InvalidBody_Returns400() throws Exception {
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllItems_ReturnsPage() throws Exception {
        ItemDto itemDto = ItemDto.builder().id(1L).build();
        Mockito.when(itemService.getAllItems(any()))
                .thenReturn(new PageImpl<>(Collections.singletonList(itemDto)));
        mockMvc.perform(get("/api/items?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void deleteItem_NotFound_Returns404() throws Exception {
        Mockito.doThrow(new by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException("Item not found"))
                .when(itemService).deleteItem(777L);
        mockMvc.perform(delete("/api/items/777"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateItem_InvalidBody_Returns400() throws Exception {
        mockMvc.perform(put("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}
