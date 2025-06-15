package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.service.PaymentService;
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
 * Тесты контроллера Payment
 */
@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Создание Payment — должно вернуть 201")
    void createPayment_ReturnsCreated() throws Exception {
        PaymentDto dto = PaymentDto.builder()
                .id(1L)
                .duty(100.0)
                .vat(20.0)
                .excise(5.0)
                .build();

        Mockito.when(paymentService.createPayment(any())).thenReturn(dto);

        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.duty", is(dto.getDuty())));
    }

    @Test
    @DisplayName("Получение Payment по id — должно вернуть 200")
    void getPaymentById_ReturnsOk() throws Exception {
        PaymentDto dto = PaymentDto.builder()
                .id(2L)
                .duty(120.0)
                .vat(24.0)
                .excise(8.0)
                .build();

        Mockito.when(paymentService.getPaymentById(2L)).thenReturn(dto);

        mockMvc.perform(get("/api/payments/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.duty", is(dto.getDuty())));
    }

    @Test
    @DisplayName("Удаление Payment — должно вернуть 204")
    void deletePayment_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/payments/3"))
                .andExpect(status().isNoContent());
        Mockito.verify(paymentService).deletePayment(3L);
    }
}
