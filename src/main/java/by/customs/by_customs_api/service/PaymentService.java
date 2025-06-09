package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    PaymentDto create(PaymentDto paymentDto);

    PaymentDto getById(Long id);

    List<PaymentDto> getAll();

    PaymentDto update(Long id, PaymentDto paymentDto);

    void delete(Long id);
}
