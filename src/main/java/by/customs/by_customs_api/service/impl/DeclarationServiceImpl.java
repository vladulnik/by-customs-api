package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.exception.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.DeclarationMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.service.DeclarationService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional  // по умолчанию: create/update/delete в транзакции
public class DeclarationServiceImpl implements DeclarationService {

    private final DeclarationRepository repository;
    private final DeclarationMapper mapper;

    @Override
    public DeclarationDto create(DeclarationDto dto) {
        DeclarationEntity entity = mapper.toEntity(dto);
        DeclarationEntity saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DeclarationDto getById(Long id) {
        DeclarationEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaration not found with id: " + id));
        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeclarationDto> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    @Override
    public DeclarationDto update(Long id, DeclarationDto dto) {
        DeclarationEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaration not found with id: " + id));
        existing.setNumber(dto.getNumber());
        existing.setDate(dto.getDate());
        return mapper.toDto(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Declaration not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
