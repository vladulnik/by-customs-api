package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.DeclarationMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeclarationServiceImplTest {

    @Mock
    private DeclarationRepository repository;
    @Mock
    private DeclarationMapper mapper;

    @InjectMocks
    private DeclarationServiceImpl service;

    public DeclarationServiceImplTest() {
        MockitoAnnotations.openMocks(this);
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
    void getAll_ReturnsPageOfDtos() {
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
        DeclarationEntity entity = new DeclarationEntity();
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.deleteDeclaration(1L);

        verify(repository).delete(entity);
    }


    @Test
    void deleteDeclaration_WhenNotFound_Throws() {
        when(repository.findById(123L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.deleteDeclaration(123L));
    }
}
