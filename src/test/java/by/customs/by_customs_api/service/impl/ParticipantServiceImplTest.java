package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ParticipantEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.ParticipantMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipantServiceImplTest {

    private ParticipantRepository participantRepository;
    private DeclarationRepository declarationRepository;
    private ParticipantMapper participantMapper;
    private ParticipantServiceImpl service;

    @BeforeEach
    void setUp() {
        participantRepository = mock(ParticipantRepository.class);
        declarationRepository = mock(DeclarationRepository.class);
        participantMapper = mock(ParticipantMapper.class);
        service = new ParticipantServiceImpl(participantRepository, declarationRepository, participantMapper);
    }

    @Test
    void create_ShouldSaveAndReturnDto() {
        ParticipantDto dto = ParticipantDto.builder().declarationId(11L).build();
        ParticipantEntity entity = new ParticipantEntity();
        DeclarationEntity decl = new DeclarationEntity();
        decl.setId(11L);

        when(declarationRepository.findById(11L)).thenReturn(Optional.of(decl));
        when(participantMapper.toEntity(dto)).thenReturn(entity);
        when(participantRepository.save(entity)).thenReturn(entity);
        when(participantMapper.toDto(entity)).thenReturn(dto);

        ParticipantDto result = service.createParticipant(dto);

        assertNotNull(result);
        verify(participantRepository).save(entity);
    }

    @Test
    void getById_WhenFound_ReturnsDto() {
        ParticipantEntity entity = new ParticipantEntity();
        entity.setId(123L);
        ParticipantDto dto = ParticipantDto.builder().id(123L).build();

        when(participantRepository.findById(123L)).thenReturn(Optional.of(entity));
        when(participantMapper.toDto(entity)).thenReturn(dto);

        ParticipantDto result = service.getParticipantById(123L);

        assertNotNull(result);
        assertEquals(123L, result.getId());
    }

    @Test
    void getById_WhenNotFound_Throws() {
        when(participantRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getParticipantById(999L));
    }
}
