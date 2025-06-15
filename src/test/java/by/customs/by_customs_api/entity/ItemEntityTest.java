package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemEntityTest {

    @Test
    void canSetDeclaration() {
        DeclarationEntity declaration = new DeclarationEntity();
        ItemEntity item = new ItemEntity();

        item.setDeclaration(declaration);
        assertEquals(declaration, item.getDeclaration());
    }
}
