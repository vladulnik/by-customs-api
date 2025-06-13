package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.entity.ParticipantEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.ParticipantMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class ParticipantServiceImplTest {

    private ParticipantRepository participantRepository;
    private DeclarationRepository declarationRepository;
    private ParticipantMapper participantMapper;
    private ParticipantServiceImpl service;

    @BeforeEach
    public void init() {
        participantRepository = Mockito.mock(ParticipantRepository.class);
        declarationRepository = Mockito.mock(DeclarationRepository.class);
        participantMapper = Mappers.getMapper(ParticipantMapper.class);
        service = new ParticipantServiceImpl(participantRepository, declarationRepository, participantMapper);
    }

    @Test
    public void createShouldThrowIfDeclarationMissing() {
        Mockito.when(declarationRepository.findById(2L)).thenReturn(Optional.empty());
        ParticipantDto dto = new ParticipantDto(null, "Name", "Addr", 2L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.create(dto));
    }

    @Test
    public void getByIdShouldReturnDto() {
        ParticipantEntity ent = new ParticipantEntity(3L, "Name", "Addr", null);
        Mockito.when(participantRepository.findById(3L)).thenReturn(Optional.of(ent));
        ParticipantDto dto = service.getById(3L);
        Assertions.assertEquals("Name", dto.getName());
    }
}
