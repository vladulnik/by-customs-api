package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.entity.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toDto(ItemEntity entity);
    ItemEntity toEntity(ItemDto dto);
}
