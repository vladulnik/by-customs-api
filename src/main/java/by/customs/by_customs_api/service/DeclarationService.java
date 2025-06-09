package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.DeclarationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeclarationService {

    DeclarationDto create(DeclarationDto dto);

    DeclarationDto getById(Long id);

    /**
     * Получить страницу деклараций с поддержкой пагинации и сортировки.
     */
    Page<DeclarationDto> getAll(Pageable pageable);

    DeclarationDto update(Long id, DeclarationDto dto);

    void delete(Long id);
}
