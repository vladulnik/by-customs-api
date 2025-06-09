package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ParticipantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    @Mapping(source = "declaration.id", target = "declarationId")
    ParticipantDto toDto(ParticipantEntity entity);

    @Mapping(target = "declaration", source = "declarationId")
    ParticipantEntity toEntity(ParticipantDto dto);

    default DeclarationEntity map(Long declarationId) {
        if (declarationId == null) {
            return null;
        }
        DeclarationEntity decl = new DeclarationEntity();
        decl.setId(declarationId);
        return decl;
    }
}
