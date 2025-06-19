package by.customs.by_customs_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantDtoTest {

    @Test
    void testGettersAndSettersAndBuilder() {
        ParticipantDto dto = ParticipantDto.builder()
                .id(1L)
                .name("Name")
                .address("Address")
                .declarationId(2L)
                .build();

        assertEquals(1L, dto.getId());
        assertEquals("Name", dto.getName());
        assertEquals("Address", dto.getAddress());
        assertEquals(2L, dto.getDeclarationId());

        dto.setName("Another");
        assertEquals("Another", dto.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        ParticipantDto dto1 = ParticipantDto.builder().id(1L).build();
        ParticipantDto dto2 = ParticipantDto.builder().id(1L).build();
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        ParticipantDto dto = ParticipantDto.builder().id(1L).build();
        assertTrue(dto.toString().contains("ParticipantDto"));
    }
}
