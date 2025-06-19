package by.customs.by_customs_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDtoTest {

    @Test
    void testGettersAndSettersAndBuilder() {
        ItemDto dto = ItemDto.builder()
                .id(1L)
                .hsCode("1000")
                .value(123.45)
                .weight(10.5)
                .originCountry("BY")
                .declarationId(2L)
                .build();

        assertEquals(1L, dto.getId());
        assertEquals("1000", dto.getHsCode());
        assertEquals(123.45, dto.getValue());
        assertEquals(10.5, dto.getWeight());
        assertEquals("BY", dto.getOriginCountry());
        assertEquals(2L, dto.getDeclarationId());

        dto.setOriginCountry("RU");
        assertEquals("RU", dto.getOriginCountry());
    }

    @Test
    void testEqualsAndHashCode() {
        ItemDto dto1 = ItemDto.builder().id(1L).build();
        ItemDto dto2 = ItemDto.builder().id(1L).build();
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        ItemDto dto = ItemDto.builder().id(1L).build();
        assertTrue(dto.toString().contains("ItemDto"));
    }
}
