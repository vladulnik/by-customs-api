package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.PaymentEntity;
import by.customs.by_customs_api.exception.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.PaymentMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    private PaymentRepository paymentRepository;
    private DeclarationRepository declarationRepository;
    private PaymentMapper paymentMapper;
    private PaymentServiceImpl service;

    @BeforeEach
    public void setup() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        declarationRepository = Mockito.mock(DeclarationRepository.class);
        paymentMapper = Mappers.getMapper(PaymentMapper.class);
        service = new PaymentServiceImpl(paymentRepository, declarationRepository, paymentMapper);
    }

    @Test
    public void createShouldThrowIfDeclarationMissing() {
        Mockito.when(declarationRepository.findById(5L)).thenReturn(Optional.empty());
        PaymentDto dto = new PaymentDto(null, 1.0, 0.5, 0.2, 5L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.create(dto));
    }

    @Test
    public void getByIdShouldReturnDto() {
        DeclarationEntity decl = new DeclarationEntity();
        decl.setId(1L);
        PaymentEntity ent = new PaymentEntity(2L, 1.0, 0.5, 0.2, decl);
        Mockito.when(paymentRepository.findById(2L)).thenReturn(Optional.of(ent));
        PaymentDto dto = service.getById(2L);
        Assertions.assertEquals(2L, dto.getId());
        Assertions.assertEquals(1.0, dto.getDuty());
        Assertions.assertEquals(1L, dto.getDeclarationId());
    }
}
