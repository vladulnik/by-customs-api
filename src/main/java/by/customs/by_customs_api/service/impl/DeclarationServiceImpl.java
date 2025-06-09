package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.exception.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.DeclarationMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.service.DeclarationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeclarationServiceImpl implements DeclarationService {

    private final DeclarationRepository declarationRepository;
    private final DeclarationMapper declarationMapper;

    public DeclarationServiceImpl(DeclarationRepository declarationRepository,
                                  DeclarationMapper declarationMapper) {
        this.declarationRepository = declarationRepository;
        this.declarationMapper = declarationMapper;
    }

    @Override
    public DeclarationDto create(DeclarationDto dto) {
        DeclarationEntity e = declarationMapper.toEntity(dto);
        return declarationMapper.toDto(declarationRepository.save(e));
    }

    @Override
    public DeclarationDto getById(Long id) {
        DeclarationEntity e = declarationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Declaration not found: " + id));
        return declarationMapper.toDto(e);
    }

    @Override
    public List<DeclarationDto> getAll() {
        return declarationRepository.findAll()
                .stream()
                .map(declarationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeclarationDto update(Long id, DeclarationDto dto) {
        DeclarationEntity existing = declarationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Declaration not found: " + id));
        existing.setNumber(dto.getNumber());
        existing.setDate(dto.getDate());
        return declarationMapper.toDto(declarationRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!declarationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Declaration not found: " + id);
        }
        declarationRepository.deleteById(id);
    }
}
