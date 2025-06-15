package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantEntityTest {

    @Test
    void canSetDeclaration() {
        DeclarationEntity declaration = new DeclarationEntity();
        ParticipantEntity participant = new ParticipantEntity();

        participant.setDeclaration(declaration);
        assertEquals(declaration, participant.getDeclaration());
    }
}
