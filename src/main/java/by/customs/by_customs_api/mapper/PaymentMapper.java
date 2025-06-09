package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto toDto(PaymentEntity entity);
    PaymentEntity toEntity(PaymentDto dto);
}
