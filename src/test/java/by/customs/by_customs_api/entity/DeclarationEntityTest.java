package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeclarationEntityTest {

    @Test
    void testBuilderCreatesValidObject() {
        DeclarationEntity declaration = DeclarationEntity.builder()
                .id(1L)
                .number("DEC123")
                .date(LocalDate.of(2025, 6, 8))
                .items(Collections.emptyList())
                .build();

        assertNotNull(declaration);
        assertEquals(1L, declaration.getId());
        assertEquals("DEC123", declaration.getNumber());
        assertEquals(LocalDate.of(2025, 6, 8), declaration.getDate());
        assertEquals(0, declaration.getItems().size());
    }

    @Test
    void testItemsAssociation() {
        DeclarationEntity declaration = new DeclarationEntity();
        declaration.setId(1L);
        declaration.setNumber("DEC999");
        declaration.setDate(LocalDate.now());

        ItemEntity item = ItemEntity.builder()
                .id(2L)
                .hsCode("8471300000")
                .value(200.0)
                .weight(1.1)
                .originCountry("PL")
                .declaration(declaration)
                .build();

        declaration.setItems(List.of(item));

        assertEquals(1, declaration.getItems().size());
        assertEquals(declaration, declaration.getItems().get(0).getDeclaration());
    }
}
