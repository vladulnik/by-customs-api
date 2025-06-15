package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.entity.PaymentEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.PaymentMapper;
import by.customs.by_customs_api.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    @Mock
    private PaymentRepository repository;
    @Mock
    private PaymentMapper mapper;

    @InjectMocks
    private PaymentServiceImpl service;

    public PaymentServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldSaveAndReturnDto() {
        PaymentDto dto = PaymentDto.builder().build();
        PaymentEntity entity = new PaymentEntity();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        PaymentDto result = service.createPayment(dto);

        assertNotNull(result);
        verify(repository).save(entity);
    }

    @Test
    void getById_WhenFound_ReturnsDto() {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(123L);
        PaymentDto dto = PaymentDto.builder().id(123L).build();

        when(repository.findById(123L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        PaymentDto result = service.getPaymentById(123L);

        assertNotNull(result);
        assertEquals(123L, result.getId());
    }

    @Test
    void getById_WhenNotFound_Throws() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getPaymentById(999L));
    }
}
