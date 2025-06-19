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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Test
    void createParticipant_WhenDeclarationNotFound_Throws() {
        ParticipantDto dto = ParticipantDto.builder().declarationId(111L).build();
        when(declarationRepository.findById(111L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.createParticipant(dto));
    }

    @Test
    void updateParticipant_WhenParticipantNotFound_Throws() {
        ParticipantDto dto = ParticipantDto.builder().id(88L).declarationId(33L).build();
        when(participantRepository.findById(88L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.updateParticipant(88L, dto));
    }

    @Test
    void updateParticipant_WhenDeclarationNotFound_Throws() {
        ParticipantDto dto = ParticipantDto.builder().id(8L).declarationId(99L).build();
        ParticipantEntity entity = new ParticipantEntity();
        when(participantRepository.findById(8L)).thenReturn(Optional.of(entity));
        when(declarationRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.updateParticipant(8L, dto));
    }

    @Test
    void deleteParticipant_WhenNotFound_Throws() {
        when(participantRepository.existsById(77L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> service.deleteParticipant(77L));
    }

    @Test
    void getAllParticipants_EmptyPage_ReturnsEmptyPage() {
        when(participantRepository.findAll(any(Pageable.class)))
                .thenReturn(Page.empty());
        Page<ParticipantDto> page = service.getAllParticipants(PageRequest.of(0, 5));
        assertTrue(page.isEmpty());
    }
}
