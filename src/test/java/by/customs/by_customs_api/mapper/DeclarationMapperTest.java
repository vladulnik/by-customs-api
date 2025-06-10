package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeclarationMapperTest {

    private final DeclarationMapper mapper = Mappers.getMapper(DeclarationMapper.class);

    @Test
    void toDtoAndBack() {
        DeclarationEntity entity = new DeclarationEntity();
        entity.setId(10L);
        entity.setNumber("NUM1");
        entity.setDate(LocalDate.of(2025, 6, 10));

        DeclarationDto dto = mapper.toDto(entity);
        assertEquals(10L, dto.getId());
        assertEquals("NUM1", dto.getNumber());
        assertEquals(LocalDate.of(2025, 6, 10), dto.getDate());

        DeclarationEntity back = mapper.toEntity(dto);
        assertEquals(entity.getId(), back.getId());
        assertEquals(entity.getNumber(), back.getNumber());
        assertEquals(entity.getDate(), back.getDate());
    }
}
