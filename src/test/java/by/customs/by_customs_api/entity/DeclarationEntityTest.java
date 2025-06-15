package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationEntityTest {

    @Test
    void itemsList_ShouldBeInitialized() {
        DeclarationEntity declaration = new DeclarationEntity();
        assertNotNull(declaration.getItems(), "items должен быть инициализирован");
        assertEquals(0, declaration.getItems().size());
    }

    @Test
    void participantsList_ShouldBeInitialized() {
        DeclarationEntity declaration = new DeclarationEntity();
        assertNotNull(declaration.getParticipants(), "participants должен быть инициализирован");
        assertEquals(0, declaration.getParticipants().size());
    }

    @Test
    void paymentsList_ShouldBeInitialized() {
        DeclarationEntity declaration = new DeclarationEntity();
        assertNotNull(declaration.getPayments(), "payments должен быть инициализирован");
        assertEquals(0, declaration.getPayments().size());
    }

    @Test
    void addItem_ShouldAddItemAndSetBidirectionalLink() {
        DeclarationEntity declaration = new DeclarationEntity();
        ItemEntity item = new ItemEntity();

        declaration.addItem(item);

        assertTrue(declaration.getItems().contains(item));
        assertEquals(declaration, item.getDeclaration());
    }

    @Test
    void addParticipant_ShouldAddParticipantAndSetBidirectionalLink() {
        DeclarationEntity declaration = new DeclarationEntity();
        ParticipantEntity participant = new ParticipantEntity();

        declaration.addParticipant(participant);

        assertTrue(declaration.getParticipants().contains(participant));
        assertEquals(declaration, participant.getDeclaration());
    }

    @Test
    void addPayment_ShouldAddPaymentAndSetBidirectionalLink() {
        DeclarationEntity declaration = new DeclarationEntity();
        PaymentEntity payment = new PaymentEntity();

        declaration.addPayment(payment);

        assertTrue(declaration.getPayments().contains(payment));
        assertEquals(declaration, payment.getDeclaration());
    }
}
