package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ParticipantEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantMapperTest {

    private final ParticipantMapper mapper = Mappers.getMapper(ParticipantMapper.class);

    @Test
    void toEntity_ShouldMapFields() {
        ParticipantDto dto = ParticipantDto.builder()
                .id(1L)
                .name("ООО Рога и Копыта")
                .address("Минск, ул. Ленина, 1")
                .declarationId(200L)
                .build();

        ParticipantEntity entity = mapper.toEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getAddress(), entity.getAddress());
        // Проверить связь declaration, если custom mapping
    }

    @Test
    void toDto_ShouldMapFields() {
        DeclarationEntity declaration = new DeclarationEntity();
        declaration.setId(200L);

        ParticipantEntity entity = new ParticipantEntity();
        entity.setId(2L);
        entity.setName("Test Name");
        entity.setAddress("Test Address");
        entity.setDeclaration(declaration);

        ParticipantDto dto = mapper.toDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getAddress(), dto.getAddress());
        assertEquals(declaration.getId(), dto.getDeclarationId());
    }
}
