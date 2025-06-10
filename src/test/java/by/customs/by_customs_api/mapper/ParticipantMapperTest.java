package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ParticipantEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class ParticipantMapperTest {

    private final ParticipantMapper mapper = Mappers.getMapper(ParticipantMapper.class);

    @Test
    void toDtoAndBack() {
        DeclarationEntity decl = new DeclarationEntity();
        decl.setId(11L);

        ParticipantEntity entity = new ParticipantEntity();
        entity.setId(4L);
        entity.setName("John Doe");
        entity.setAddress("123 Main St");
        entity.setDeclaration(decl);

        ParticipantDto dto = mapper.toDto(entity);
        assertEquals(4L, dto.getId());
        assertEquals("John Doe", dto.getName());
        assertEquals("123 Main St", dto.getAddress());
        assertEquals(11L, dto.getDeclarationId());

        ParticipantEntity back = mapper.toEntity(dto);
        assertEquals(dto.getId(), back.getId());
        assertEquals(dto.getName(), back.getName());
        assertEquals(dto.getAddress(), back.getAddress());
        assertNotNull(back.getDeclaration());
        assertEquals(11L, back.getDeclaration().getId());
    }
}
