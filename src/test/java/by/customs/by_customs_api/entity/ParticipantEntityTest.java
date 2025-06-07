package by.customs.by_customs_api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParticipantEntityTest {

    @Test
    void shouldCreateParticipantWithNameAndAddress() {
        ParticipantEntity participant = ParticipantEntity.builder()
                .name("ООО ЭкспортТорг")
                .address("Минск, ул. Центральная, 1")
                .build();

        assertEquals("ООО ЭкспортТорг", participant.getName());
        assertEquals("Минск, ул. Центральная, 1", participant.getAddress());
    }

    @Test
    void shouldSetAndGetId() {
        ParticipantEntity participant = new ParticipantEntity();
        participant.setId(5L);

        assertEquals(5L, participant.getId());
    }

    @Test
    void shouldCreateNonNullParticipant() {
        ParticipantEntity participant = ParticipantEntity.builder().build();
        assertNotNull(participant);
    }
}
