package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
                .hsCode("8471300000")
                .value(1200.50)
                .weight(2.5)
                .originCountry("CN")
                .declaration(declaration)
                .build();

        assertEquals(10L, item.getId());
        assertEquals("8471300000", item.getHsCode());
        assertEquals(1200.50, item.getValue());
        assertEquals(2.5, item.getWeight());
        assertEquals("CN", item.getOriginCountry());
        assertEquals(declaration, item.getDeclaration());
    }

    @Test
    void testSetters() {
        ItemEntity item = new ItemEntity();
        item.setId(5L);
        item.setHsCode("8528724000");
        item.setValue(899.99);
        item.setWeight(1.2);
        item.setOriginCountry("KR");

        DeclarationEntity declaration = new DeclarationEntity();
        declaration.setId(3L);
        item.setDeclaration(declaration);

        assertEquals(5L, item.getId());
        assertEquals("8528724000", item.getHsCode());
        assertEquals(899.99, item.getValue());
        assertEquals(1.2, item.getWeight());
        assertEquals("KR", item.getOriginCountry());
        assertEquals(declaration, item.getDeclaration());
    }
}
