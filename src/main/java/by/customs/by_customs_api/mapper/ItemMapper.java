package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "declaration.id", target = "declarationId")
    ItemDto toDto(ItemEntity entity);

    @Mapping(target = "declaration", source = "declarationId")
    ItemEntity toEntity(ItemDto dto);

    default DeclarationEntity map(Long declarationId) {
        if (declarationId == null) return null;
        DeclarationEntity d = new DeclarationEntity();
        d.setId(declarationId);
        return d;
    }
}
