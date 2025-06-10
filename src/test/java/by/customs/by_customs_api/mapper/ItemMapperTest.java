package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ItemEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class ItemMapperTest {

    private final ItemMapper mapper = Mappers.getMapper(ItemMapper.class);

    @Test
    void toDtoSetsAllFields() {
        DeclarationEntity decl = new DeclarationEntity();
        decl.setId(7L);

        ItemEntity entity = new ItemEntity();
        entity.setId(3L);
        entity.setHsCode("HS123");
        entity.setValue(100.0);
        entity.setWeight(2.5);
        entity.setOriginCountry("CN");
        entity.setDeclaration(decl);

        ItemDto dto = mapper.toDto(entity);
        assertEquals(3L, dto.getId());
        assertEquals("HS123", dto.getHsCode());
        assertEquals(100.0, dto.getValue());
        assertEquals(2.5, dto.getWeight());
        assertEquals("CN", dto.getOriginCountry());
        assertEquals(7L, dto.getDeclarationId());
    }

    @Test
    void toEntityCreatesDeclarationEntity() {
        ItemDto dto = new ItemDto();
        dto.setId(5L);
        dto.setHsCode("HS999");
        dto.setValue(50.0);
        dto.setWeight(1.0);
        dto.setOriginCountry("RU");
        dto.setDeclarationId(9L);

        ItemEntity entity = mapper.toEntity(dto);
        assertEquals(5L, entity.getId());
        assertEquals("HS999", entity.getHsCode());
        assertEquals(50.0, entity.getValue());
        assertEquals(1.0, entity.getWeight());
        assertEquals("RU", entity.getOriginCountry());
        assertNotNull(entity.getDeclaration());
        assertEquals(9L, entity.getDeclaration().getId());
    }
}
