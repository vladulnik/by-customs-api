package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.entity.ParticipantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    ParticipantDto toDto(ParticipantEntity entity);
    ParticipantEntity toEntity(ParticipantDto dto);
}
