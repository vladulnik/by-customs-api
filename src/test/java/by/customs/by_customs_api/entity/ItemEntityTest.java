package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemEntityTest {

    @Test
    void testBuilderAndGetters() {
        DeclarationEntity declaration = DeclarationEntity.builder()
                .id(1L)
                .number("DEC001")
                .date(null)
                .build();

        ItemEntity item = ItemEntity.builder()
                .id(10L)
                .description("Ноутбук")
                .weight(2.5)
                .declaration(declaration)
                .build();

        assertEquals(10L, item.getId());
        assertEquals("Ноутбук", item.getDescription());
        assertEquals(2.5, item.getWeight());
        assertEquals(declaration, item.getDeclaration());
    }
}
