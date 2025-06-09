package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.PaymentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    PaymentDto create(PaymentDto dto);

    PaymentDto getById(Long id);

    Page<PaymentDto> getAll(Pageable pageable);

    PaymentDto update(Long id, PaymentDto dto);

    void delete(Long id);
}
