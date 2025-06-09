package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ParticipantEntity;
import by.customs.by_customs_api.exception.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.ParticipantMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.ParticipantRepository;
import by.customs.by_customs_api.service.ParticipantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final DeclarationRepository declarationRepository;
    private final ParticipantMapper participantMapper;

    public ParticipantServiceImpl(ParticipantRepository participantRepository,
                                  DeclarationRepository declarationRepository,
                                  ParticipantMapper participantMapper) {
        this.participantRepository = participantRepository;
        this.declarationRepository = declarationRepository;
        this.participantMapper = participantMapper;
    }

    @Override
    public ParticipantDto create(ParticipantDto dto) {
        DeclarationEntity decl = declarationRepository.findById(dto.getDeclarationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaration not found with id: " + dto.getDeclarationId()));
        ParticipantEntity e = participantMapper.toEntity(dto);
        e.setDeclaration(decl);
        return participantMapper.toDto(participantRepository.save(e));
    }

    @Override
    public ParticipantDto getById(Long id) {
        ParticipantEntity e = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found: " + id));
        return participantMapper.toDto(e);
    }

    @Override
    public List<ParticipantDto> getAll() {
        return participantRepository.findAll()
                .stream()
                .map(participantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipantDto update(Long id, ParticipantDto dto) {
        ParticipantEntity existing = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found: " + id));
        DeclarationEntity decl = declarationRepository.findById(dto.getDeclarationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaration not found with id: " + dto.getDeclarationId()));

        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setDeclaration(decl);

        return participantMapper.toDto(participantRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Participant not found: " + id);
        }
        participantRepository.deleteById(id);
    }
}
