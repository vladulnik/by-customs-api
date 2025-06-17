package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.PaymentEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.PaymentMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    private PaymentRepository paymentRepository;
    private DeclarationRepository declarationRepository;
    private PaymentMapper paymentMapper;
    private PaymentServiceImpl service;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        declarationRepository = mock(DeclarationRepository.class);
        paymentMapper = mock(PaymentMapper.class);
        service = new PaymentServiceImpl(paymentRepository, declarationRepository, paymentMapper);
    }

    @Test
    void create_ShouldSaveAndReturnDto() {
        PaymentDto dto = PaymentDto.builder().declarationId(15L).build();
        PaymentEntity entity = new PaymentEntity();
        DeclarationEntity decl = new DeclarationEntity();
        decl.setId(15L);

        when(declarationRepository.findById(15L)).thenReturn(Optional.of(decl));
        when(paymentMapper.toEntity(dto)).thenReturn(entity);
        when(paymentRepository.save(entity)).thenReturn(entity);
        when(paymentMapper.toDto(entity)).thenReturn(dto);

        PaymentDto result = service.createPayment(dto);

        assertNotNull(result);
        verify(paymentRepository).save(entity);
    }

    @Test
    void getById_WhenFound_ReturnsDto() {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(123L);
        PaymentDto dto = PaymentDto.builder().id(123L).build();

        when(paymentRepository.findById(123L)).thenReturn(Optional.of(entity));
        when(paymentMapper.toDto(entity)).thenReturn(dto);

        PaymentDto result = service.getPaymentById(123L);

        assertNotNull(result);
        assertEquals(123L, result.getId());
    }

    @Test
    void getById_WhenNotFound_Throws() {
        when(paymentRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getPaymentById(999L));
    }
}
