package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeclarationMapper {
    DeclarationDto toDto(DeclarationEntity entity);

    DeclarationEntity toEntity(DeclarationDto dto);
}
