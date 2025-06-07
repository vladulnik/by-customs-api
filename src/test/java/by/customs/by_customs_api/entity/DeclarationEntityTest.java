package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeclarationEntityTest {

    @Test
    void testBuilderAndGetters() {
        DeclarationEntity declaration = DeclarationEntity.builder()
                .id(1L)
                .number("DEC001")
                .date(LocalDate.of(2025, 6, 7))
                .items(Collections.emptyList())
                .build();

        assertEquals(1L, declaration.getId());
        assertEquals("DEC001", declaration.getNumber());
        assertEquals(LocalDate.of(2025, 6, 7), declaration.getDate());
        assertNotNull(declaration.getItems());
        assertEquals(0, declaration.getItems().size());
    }

    @Test
    void testAddItem() {
        DeclarationEntity declaration = DeclarationEntity.builder()
                .id(1L)
                .number("DEC002")
                .date(LocalDate.now())
                .build();

        ItemEntity item = ItemEntity.builder()
                .id(100L)
                .description("Смартфон")
                .weight(0.3)
                .declaration(declaration)
                .build();

        declaration.setItems(List.of(item));

        assertEquals(1, declaration.getItems().size());
        assertEquals("Смартфон", declaration.getItems().get(0).getDescription());
    }
}
