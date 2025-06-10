package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.exception.GlobalExceptionHandler;
import by.customs.by_customs_api.service.PaymentService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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

public class PaymentControllerTest {

    private MockMvc mockMvc;
    private PaymentService service;
    private ObjectMapper objectMapper;
    private PaymentDto testPayment;

    @BeforeEach
    public void setUp() {
        service = Mockito.mock(PaymentService.class);
        PaymentController controller = new PaymentController(service);

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        testPayment = new PaymentDto();
        testPayment.setId(1L);
        testPayment.setDuty(150.0);
        testPayment.setVat(30.0);
        testPayment.setExcise(5.0);
        testPayment.setDeclarationId(1L);
    }

    @Test
    public void getAllShouldReturnPage() throws Exception {
        Page<PaymentDto> page = new PageImpl<>(
                Collections.singletonList(testPayment),
                PageRequest.of(0, 10),
                1
        );
        given(service.getAll(any())).willReturn(page);

        mockMvc.perform(get("/api/payments")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value(1L));
    }

    @Test
    public void getByIdShouldReturnPayment() throws Exception {
        given(service.getById(1L)).willReturn(testPayment);

        mockMvc.perform(get("/api/payments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duty").value(150.0))
                .andExpect(jsonPath("$.vat").value(30.0))
                .andExpect(jsonPath("$.excise").value(5.0));
    }

    @Test
    public void createShouldReturnOk() throws Exception {
        given(service.create(any(PaymentDto.class))).willReturn(testPayment);

        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPayment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void updateShouldReturnOk() throws Exception {
        given(service.update(eq(1L), any(PaymentDto.class))).willReturn(testPayment);

        mockMvc.perform(put("/api/payments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPayment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.duty").value(150.0));
    }

    @Test
    public void deleteShouldReturnNoContent() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/payments/1"))
                .andExpect(status().isNoContent());
    }
}
