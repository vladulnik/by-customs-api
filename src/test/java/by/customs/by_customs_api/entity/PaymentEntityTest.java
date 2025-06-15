package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentEntityTest {

    @Test
    void canSetDeclaration() {
        DeclarationEntity declaration = new DeclarationEntity();
        PaymentEntity payment = new PaymentEntity();

        payment.setDeclaration(declaration);
        assertEquals(declaration, payment.getDeclaration());
    }
}
