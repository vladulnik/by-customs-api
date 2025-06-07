package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaymentEntityTest {

    @Test
    void shouldCreatePaymentWithValues() {
        PaymentEntity payment = PaymentEntity.builder()
                .duty(150.75)
                .vat(30.0)
                .build();

        assertEquals(150.75, payment.getDuty());
        assertEquals(30.0, payment.getVat());
    }

    @Test
    void shouldSetAndGetId() {
        PaymentEntity payment = new PaymentEntity();
        payment.setId(1L);

        assertEquals(1L, payment.getId());
    }

    @Test
    void shouldCreateNonNullObject() {
        PaymentEntity payment = PaymentEntity.builder().build();
        assertNotNull(payment);
    }
}
