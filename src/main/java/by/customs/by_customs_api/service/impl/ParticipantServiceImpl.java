package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.entity.ParticipantEntity;
import by.customs.by_customs_api.exception.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.ParticipantMapper;
import by.customs.by_customs_api.repository.ParticipantRepository;
import by.customs.by_customs_api.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository repository;
    private final ParticipantMapper mapper;

    @Override
    public ParticipantDto create(ParticipantDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public ParticipantDto getById(Long id) {
        ParticipantEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));
        return mapper.toDto(entity);
    }

    @Override
    public List<ParticipantDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ParticipantDto update(Long id, ParticipantDto dto) {
        ParticipantEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));

        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());

        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Participant not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
