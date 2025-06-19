package by.customs.by_customs_api.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationDtoTest {

    @Test
    void testGettersAndSettersAndBuilder() {
        DeclarationDto dto = DeclarationDto.builder()
                .id(1L)
                .number("DEC123")
                .date(LocalDate.of(2024, 6, 19))
                .items(Collections.emptyList())
                .participants(Collections.emptyList())
                .payments(Collections.emptyList())
                .build();

        assertEquals(1L, dto.getId());
        assertEquals("DEC123", dto.getNumber());
        assertEquals(LocalDate.of(2024, 6, 19), dto.getDate());
        assertNotNull(dto.getItems());
        assertNotNull(dto.getParticipants());
        assertNotNull(dto.getPayments());

        // Проверим сеттеры (если они есть)
        dto.setNumber("NEW_NUM");
        assertEquals("NEW_NUM", dto.getNumber());
    }

    @Test
    void testEqualsAndHashCode() {
        DeclarationDto dto1 = DeclarationDto.builder().id(1L).number("A").build();
        DeclarationDto dto2 = DeclarationDto.builder().id(1L).number("A").build();

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        DeclarationDto dto = DeclarationDto.builder().id(1L).number("A").build();
        assertTrue(dto.toString().contains("DeclarationDto"));
    }
}
