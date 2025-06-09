package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentDto dto) {
        return ResponseEntity.ok(paymentService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> update(@PathVariable Long id, @RequestBody PaymentDto dto) {
        return ResponseEntity.ok(paymentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
