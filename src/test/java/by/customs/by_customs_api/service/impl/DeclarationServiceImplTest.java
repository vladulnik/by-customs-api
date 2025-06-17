package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.DeclarationMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeclarationServiceImplTest {

    private DeclarationRepository repository;
    private DeclarationMapper mapper;
    private DeclarationServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(DeclarationRepository.class);
        mapper = mock(DeclarationMapper.class);
        service = new DeclarationServiceImpl(repository, mapper);
    }

    @Test
    void create_ShouldSaveAndReturnDto() {
        DeclarationDto dto = DeclarationDto.builder().number("DEC-1").date(LocalDate.now()).build();
        DeclarationEntity entity = new DeclarationEntity();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        DeclarationDto result = service.createDeclaration(dto);

        assertNotNull(result);
        verify(repository).save(entity);
    }

    @Test
    void getById_WhenFound_ReturnsDto() {
        DeclarationEntity entity = new DeclarationEntity();
        entity.setId(123L);
        DeclarationDto dto = DeclarationDto.builder().id(123L).build();

        when(repository.findById(123L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        DeclarationDto result = service.getDeclarationById(123L);

        assertNotNull(result);
        assertEquals(123L, result.getId());
    }

    @Test
    void getById_WhenNotFound_Throws() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getDeclarationById(999L));
    }

    @Test
    void getAllDeclarations_ReturnsPageOfDtos() {
        DeclarationEntity entity = new DeclarationEntity();
        DeclarationDto dto = DeclarationDto.builder().build();

        when(repository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(entity)));
        when(mapper.toDto(entity)).thenReturn(dto);

        Page<DeclarationDto> page = service.getAllDeclarations(PageRequest.of(0, 10));
        assertEquals(1, page.getTotalElements());
    }

    @Test
    void updateDeclaration_WhenFound_UpdatesAndReturnsDto() {
        DeclarationDto dto = DeclarationDto.builder().id(1L).number("NEW").build();
        DeclarationEntity entity = new DeclarationEntity();
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        DeclarationDto result = service.updateDeclaration(1L, dto);
        assertNotNull(result);
    }

    @Test
    void deleteDeclaration_WhenFound_Deletes() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteDeclaration(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deleteDeclaration_WhenNotFound_Throws() {
        when(repository.existsById(123L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.deleteDeclaration(123L));
    }
}
