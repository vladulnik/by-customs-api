package by.customs.by_customs_api.mapper;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationMapperTest {

    private final DeclarationMapper mapper = Mappers.getMapper(DeclarationMapper.class);

//    @Test
//    void toEntity_ShouldMapAllFields() {
//        DeclarationDto dto = DeclarationDto.builder()
//                .id(1L)
//                .number("DEC-001")
//                .date(LocalDate.of(2024, 6, 1))
//                .items(Collections.emptyList())
//                .participants(Collections.emptyList())
//                .payments(Collections.emptyList())
//                .build();
//
//        DeclarationEntity entity = mapper.toEntity(dto);
//
//        assertEquals(dto.getId(), entity.getId());
//        assertEquals(dto.getNumber(), entity.getNumber());
//        assertEquals(dto.getDate(), entity.getDate());
//        assertNotNull(entity.getItems());
//        assertNotNull(entity.getParticipants());
//        assertNotNull(entity.getPayments());
//    }
//
//    @Test
//    void toDto_ShouldMapAllFields() {
//        DeclarationEntity entity = new DeclarationEntity();
//        entity.setId(2L);
//        entity.setNumber("DEC-002");
//        entity.setDate(LocalDate.of(2024, 6, 2));
//        entity.setItems(Collections.emptyList());
//        entity.setParticipants(Collections.emptyList());
//        entity.setPayments(Collections.emptyList());
//
//        DeclarationDto dto = mapper.toDto(entity);
//
//        assertEquals(entity.getId(), dto.getId());
//        assertEquals(entity.getNumber(), dto.getNumber());
//        assertEquals(entity.getDate(), dto.getDate());
//        assertNotNull(dto.getItems());
//        assertNotNull(dto.getParticipants());
//        assertNotNull(dto.getPayments());
//    }


    @Test
    void toEntity_ShouldMapAllFields() {
        DeclarationDto dto = DeclarationDto.builder()
                .id(1L)
                .number("D1")
                .date(LocalDate.now())
                .items(List.of())
                .participants(List.of())
                .payments(List.of())
                .build();

        DeclarationEntity entity = mapper.toEntity(dto);

        assertEquals(dto.getNumber(), entity.getNumber());
        assertEquals(dto.getDate(), entity.getDate());
        assertNotNull(entity.getItems());
        assertNotNull(entity.getParticipants());
        assertNotNull(entity.getPayments());
    }

    @Test
    void toDto_ShouldMapAllFields() {
        DeclarationEntity entity = new DeclarationEntity();
        entity.setId(2L);
        entity.setNumber("D2");
        entity.setDate(LocalDate.now());
        entity.setItems(List.of());
        entity.setParticipants(List.of());
        entity.setPayments(List.of());

        DeclarationDto dto = mapper.toDto(entity);

        assertEquals(entity.getNumber(), dto.getNumber());
        assertEquals(entity.getDate(), dto.getDate());
        assertNotNull(dto.getItems());
        assertNotNull(dto.getParticipants());
        assertNotNull(dto.getPayments());
    }

    @Test
    void toEntity_NullCollections_ShouldNotThrow() {
        DeclarationDto dto = DeclarationDto.builder()
                .id(3L)
                .number("D3")
                .date(LocalDate.now())
                .build();

        DeclarationEntity entity = mapper.toEntity(dto);
        assertNotNull(entity.getItems());
        assertNotNull(entity.getParticipants());
        assertNotNull(entity.getPayments());
    }

    @Test
    void toDto_NullCollections_ShouldNotThrow() {
        DeclarationEntity entity = new DeclarationEntity();
        entity.setId(4L);
        entity.setNumber("D4");
        entity.setDate(LocalDate.now());

        DeclarationDto dto = mapper.toDto(entity);
        assertNotNull(dto.getItems());
        assertNotNull(dto.getParticipants());
        assertNotNull(dto.getPayments());
    }
}
