package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.PaymentDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.PaymentEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentMapperTest {

    private final PaymentMapper mapper = Mappers.getMapper(PaymentMapper.class);

    @Test
    void toDtoAndBack() {
        DeclarationEntity decl = new DeclarationEntity();
        decl.setId(20L);

        PaymentEntity entity = new PaymentEntity();
        entity.setId(8L);
        entity.setDuty(1.1);
        entity.setVat(2.2);
        entity.setExcise(3.3);
        entity.setDeclaration(decl);

        PaymentDto dto = mapper.toDto(entity);
        assertEquals(8L, dto.getId());
        assertEquals(1.1, dto.getDuty());
        assertEquals(2.2, dto.getVat());
        assertEquals(3.3, dto.getExcise());
        assertEquals(20L, dto.getDeclarationId());

        PaymentEntity back = mapper.toEntity(dto);
        assertEquals(dto.getId(), back.getId());
        assertEquals(dto.getDuty(), back.getDuty());
        assertEquals(dto.getVat(), back.getVat());
        assertEquals(dto.getExcise(), back.getExcise());
        assertNotNull(back.getDeclaration());
        assertEquals(20L, back.getDeclaration().getId());
    }
}
