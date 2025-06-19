package by.customs.by_customs_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDtoTest {

    @Test
    void testGettersAndSettersAndBuilder() {
        PaymentDto dto = PaymentDto.builder()
                .id(1L)
                .duty(10.0)
                .vat(20.0)
                .excise(5.0)
                .declarationId(3L)
                .build();

        assertEquals(1L, dto.getId());
        assertEquals(10.0, dto.getDuty());
        assertEquals(20.0, dto.getVat());
        assertEquals(5.0, dto.getExcise());
        assertEquals(3L, dto.getDeclarationId());

        dto.setDuty(15.0);
        assertEquals(15.0, dto.getDuty());
    }

    @Test
    void testEqualsAndHashCode() {
        PaymentDto dto1 = PaymentDto.builder().id(1L).build();
        PaymentDto dto2 = PaymentDto.builder().id(1L).build();
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        PaymentDto dto = PaymentDto.builder().id(1L).build();
        assertTrue(dto.toString().contains("PaymentDto"));
    }
}
