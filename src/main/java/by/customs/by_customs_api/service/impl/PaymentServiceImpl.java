package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.PaymentEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.PaymentMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.PaymentRepository;
import by.customs.by_customs_api.service.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
        DeclarationEntity decl = declarationRepository.findById(dto.getDeclarationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaration not found with id: " + dto.getDeclarationId()));
        PaymentEntity entity = paymentMapper.toEntity(dto);
        entity.setDeclaration(decl);
        return paymentMapper.toDto(paymentRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentDto getById(Long id) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found with id: " + id));
        return paymentMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PaymentDto> getAll(Pageable pageable) {
        return paymentRepository.findAll(pageable)
                .map(paymentMapper::toDto);
    }

    @Override
    public PaymentDto update(Long id, PaymentDto dto) {
        PaymentEntity existing = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found with id: " + id));
        DeclarationEntity decl = declarationRepository.findById(dto.getDeclarationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaration not found with id: " + dto.getDeclarationId()));
        existing.setDuty(dto.getDuty());
        existing.setVat(dto.getVat());
        existing.setExcise(dto.getExcise());
        existing.setDeclaration(decl);
        return paymentMapper.toDto(paymentRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }
}
