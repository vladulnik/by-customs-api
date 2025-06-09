package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto create(PaymentDto dto);
    PaymentDto getById(Long id);
    List<PaymentDto> getAll();
    PaymentDto update(Long id, PaymentDto dto);
    void delete(Long id);
}
