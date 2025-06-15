package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationMapperTest {

    private final DeclarationMapper mapper = Mappers.getMapper(DeclarationMapper.class);

    @Test
    void toEntity_ShouldMapAllFields() {
        DeclarationDto dto = DeclarationDto.builder()
                .id(1L)
                .number("DEC-001")
                .date(LocalDate.of(2024, 6, 1))
                .items(Collections.emptyList())
                .participants(Collections.emptyList())
                .payments(Collections.emptyList())
                .build();

        DeclarationEntity entity = mapper.toEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNumber(), entity.getNumber());
        assertEquals(dto.getDate(), entity.getDate());
        assertNotNull(entity.getItems());
        assertNotNull(entity.getParticipants());
        assertNotNull(entity.getPayments());
    }

    @Test
    void toDto_ShouldMapAllFields() {
        DeclarationEntity entity = new DeclarationEntity();
        entity.setId(2L);
        entity.setNumber("DEC-002");
        entity.setDate(LocalDate.of(2024, 6, 2));
        entity.setItems(Collections.emptyList());
        entity.setParticipants(Collections.emptyList());
        entity.setPayments(Collections.emptyList());

        DeclarationDto dto = mapper.toDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNumber(), dto.getNumber());
        assertEquals(entity.getDate(), dto.getDate());
        assertNotNull(dto.getItems());
        assertNotNull(dto.getParticipants());
        assertNotNull(dto.getPayments());
    }
}
