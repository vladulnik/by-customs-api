package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.PaymentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    PaymentDto createPayment(PaymentDto dto);

    PaymentDto getPaymentById(Long id);

    Page<PaymentDto> getAllPayments(Pageable pageable);

    PaymentDto updatePayment(Long id, PaymentDto dto);

    void deletePayment(Long id);
}
