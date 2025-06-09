package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.PaymentEntity;
import by.customs.by_customs_api.exception.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.PaymentMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.PaymentRepository;
import by.customs.by_customs_api.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final DeclarationRepository declarationRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              DeclarationRepository declarationRepository,
                              PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.declarationRepository = declarationRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDto create(PaymentDto dto) {
        DeclarationEntity declaration = declarationRepository.findById(dto.getDeclarationId())
                .orElseThrow(() -> new ResourceNotFoundException("Declaration not found"));

        PaymentEntity entity = paymentMapper.toEntity(dto);
        entity.setDeclaration(declaration);
        return paymentMapper.toDto(paymentRepository.save(entity));
    }

    @Override
    public PaymentDto getById(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
    }

    @Override
    public List<PaymentDto> getAll() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto update(Long id, PaymentDto dto) {
        PaymentEntity existing = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        DeclarationEntity declaration = declarationRepository.findById(dto.getDeclarationId())
                .orElseThrow(() -> new ResourceNotFoundException("Declaration not found"));

        existing.setDuty(dto.getDuty());
        existing.setVat(dto.getVat());
        existing.setExcise(dto.getExcise());
        existing.setDeclaration(declaration);

        return paymentMapper.toDto(paymentRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }
}
