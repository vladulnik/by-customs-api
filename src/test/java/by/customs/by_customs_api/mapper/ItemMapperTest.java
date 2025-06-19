package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ItemEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    private final ItemMapper mapper = Mappers.getMapper(ItemMapper.class);

    @Test
    void toEntity_ShouldMapFields() {
        ItemDto dto = ItemDto.builder()
                .id(1L)
                .hsCode("1234")
                .value(500.0)
                .weight(10.0)
                .originCountry("BY")
                .declarationId(100L)
                .build();

        ItemEntity entity = mapper.toEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getHsCode(), entity.getHsCode());
        assertEquals(dto.getValue(), entity.getValue());
        assertEquals(dto.getWeight(), entity.getWeight());
        assertEquals(dto.getOriginCountry(), entity.getOriginCountry());
    }

    @Test
    void toDto_ShouldMapFields() {
        DeclarationEntity declaration = new DeclarationEntity();
        declaration.setId(100L);

        ItemEntity entity = new ItemEntity();
        entity.setId(2L);
        entity.setHsCode("5678");
        entity.setValue(200.0);
        entity.setWeight(20.0);
        entity.setOriginCountry("RU");
        entity.setDeclaration(declaration);

        ItemDto dto = mapper.toDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getHsCode(), dto.getHsCode());
        assertEquals(entity.getValue(), dto.getValue());
        assertEquals(entity.getWeight(), dto.getWeight());
        assertEquals(entity.getOriginCountry(), dto.getOriginCountry());
        assertEquals(declaration.getId(), dto.getDeclarationId());
    }

    @Test
    void toEntityAndBack_ShouldMapFields() {
        ItemDto dto = ItemDto.builder()
                .id(1L)
                .hsCode("1000")
                .value(500.0)
                .weight(10.5)
                .originCountry("BY")
                .declarationId(7L)
                .build();

        ItemEntity entity = mapper.toEntity(dto);
        assertEquals(dto.getHsCode(), entity.getHsCode());
        assertEquals(dto.getValue(), entity.getValue());
        assertEquals(dto.getWeight(), entity.getWeight());
        assertEquals(dto.getOriginCountry(), entity.getOriginCountry());

        DeclarationEntity declaration = new DeclarationEntity();
        declaration.setId(dto.getDeclarationId());
        entity.setDeclaration(declaration);

        ItemDto back = mapper.toDto(entity);
        assertEquals(dto.getDeclarationId(), back.getDeclarationId());
    }

    @Test
    void toEntity_Null_ShouldNotThrow() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toDto_Null_ShouldNotThrow() {
        assertNull(mapper.toDto(null));
    }
}
