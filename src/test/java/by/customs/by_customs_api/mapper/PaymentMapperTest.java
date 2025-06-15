package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.PaymentEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMapperTest {

    private final PaymentMapper mapper = Mappers.getMapper(PaymentMapper.class);

    @Test
    void toEntity_ShouldMapFields() {
        PaymentDto dto = PaymentDto.builder()
                .id(1L)
                .duty(100.0)
                .vat(20.0)
                .excise(5.0)
                .declarationId(300L)
                .build();

        PaymentEntity entity = mapper.toEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getDuty(), entity.getDuty());
        assertEquals(dto.getVat(), entity.getVat());
        assertEquals(dto.getExcise(), entity.getExcise());
        // Проверить связь declaration, если custom mapping
    }

    @Test
    void toDto_ShouldMapFields() {
        DeclarationEntity declaration = new DeclarationEntity();
        declaration.setId(300L);

        PaymentEntity entity = new PaymentEntity();
        entity.setId(2L);
        entity.setDuty(111.0);
        entity.setVat(22.0);
        entity.setExcise(7.0);
        entity.setDeclaration(declaration);

        PaymentDto dto = mapper.toDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getDuty(), dto.getDuty());
        assertEquals(entity.getVat(), dto.getVat());
        assertEquals(entity.getExcise(), dto.getExcise());
        assertEquals(declaration.getId(), dto.getDeclarationId());
    }
}
