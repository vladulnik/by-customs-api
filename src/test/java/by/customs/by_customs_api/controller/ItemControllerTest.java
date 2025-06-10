package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

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

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private ObjectMapper objectMapper;
    private ItemDto testItem;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        testItem = new ItemDto();
        testItem.setId(1L);
        testItem.setHsCode("8471300000");
        testItem.setValue(1000.0);
        testItem.setWeight(2.5);
        testItem.setOriginCountry("CN");
        testItem.setDeclarationId(1L);
    }

    @Test
    void getAllShouldReturnPage() throws Exception {
        Page<ItemDto> page = new PageImpl<>(Collections.singletonList(testItem), PageRequest.of(0, 10), 1);
        given(itemService.getAll(any(Pageable.class))).willReturn(page);

        mockMvc.perform(get("/api/items").param("page", "0").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value(1L));
    }

    @Test
    void getByIdShouldReturnItem() throws Exception {
        given(itemService.getById(1L)).willReturn(testItem);

        mockMvc.perform(get("/api/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.hsCode").value("8471300000"));
    }

    @Test
    void createShouldReturnOk() throws Exception {
        given(itemService.create(any(ItemDto.class))).willReturn(testItem);

        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void updateShouldReturnOk() throws Exception {
        given(itemService.update(eq(1L), any(ItemDto.class))).willReturn(testItem);

        mockMvc.perform(put("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deleteShouldReturnNoContent() throws Exception {
        doNothing().when(itemService).delete(1L);

        mockMvc.perform(delete("/api/items/1"))
                .andExpect(status().isNoContent());
    }
}
