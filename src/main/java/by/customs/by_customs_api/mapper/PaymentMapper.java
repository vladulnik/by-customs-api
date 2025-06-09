package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mappings({
            @Mapping(source = "declaration.id", target = "declarationId")
    })
    PaymentDto toDto(PaymentEntity paymentEntity);

    @Mappings({
            @Mapping(source = "declarationId", target = "declaration")
    })
    PaymentEntity toEntity(PaymentDto paymentDto);

    default DeclarationEntity map(Long declarationId) {
        if (declarationId == null) return null;
        DeclarationEntity declaration = new DeclarationEntity();
        declaration.setId(declarationId);
        return declaration;
    }
}
