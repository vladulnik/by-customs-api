package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.exception.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.DeclarationMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class DeclarationServiceImplTest {

    private DeclarationRepository repository;
    private DeclarationMapper mapper;
    private DeclarationServiceImpl service;

    @BeforeEach
    public void init() {
        repository = Mockito.mock(DeclarationRepository.class);
        mapper = Mappers.getMapper(DeclarationMapper.class);
        service = new DeclarationServiceImpl(repository, mapper);
    }

    @Test
    public void createShouldReturnDtoWithId() {
        LocalDate date = LocalDate.of(2025, 6, 10);
        DeclarationDto input = new DeclarationDto(null, "NUM1", date);

        // Создаем сущность с помощью builder, указывая пустой список items
        DeclarationEntity saved = DeclarationEntity.builder()
                .id(1L)
                .number("NUM1")
                .date(date)
                .items(Collections.emptyList())
                .build();

        Mockito.when(repository.save(any(DeclarationEntity.class))).thenReturn(saved);

        DeclarationDto result = service.create(input);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("NUM1", result.getNumber());
        Assertions.assertEquals(date, result.getDate());
        Mockito.verify(repository).save(any(DeclarationEntity.class));
    }

    @Test
    public void getByIdShouldThrowIfNotFound() {
        Mockito.when(repository.findById(5L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.getById(5L));
    }
}
