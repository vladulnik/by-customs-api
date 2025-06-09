package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.DeclarationDto;

import java.util.List;

public interface DeclarationService {
    DeclarationDto create(DeclarationDto dto);

    DeclarationDto getById(Long id);

    List<DeclarationDto> getAll();

    DeclarationDto update(Long id, DeclarationDto dto);

    void delete(Long id);
}
