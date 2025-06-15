package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.entity.ParticipantEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.ParticipantMapper;
import by.customs.by_customs_api.repository.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipantServiceImplTest {

    @Mock
    private ParticipantRepository repository;
    @Mock
    private ParticipantMapper mapper;

    @InjectMocks
    private ParticipantServiceImpl service;

    public ParticipantServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldSaveAndReturnDto() {
        ParticipantDto dto = ParticipantDto.builder().build();
        ParticipantEntity entity = new ParticipantEntity();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        ParticipantDto result = service.createParticipant(dto);

        assertNotNull(result);
        verify(repository).save(entity);
    }

    @Test
    void getById_WhenFound_ReturnsDto() {
        ParticipantEntity entity = new ParticipantEntity();
        entity.setId(123L);
        ParticipantDto dto = ParticipantDto.builder().id(123L).build();

        when(repository.findById(123L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        ParticipantDto result = service.getParticipantById(123L);

        assertNotNull(result);
        assertEquals(123L, result.getId());
    }

    @Test
    void getById_WhenNotFound_Throws() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getParticipantById(999L));
    }
}
