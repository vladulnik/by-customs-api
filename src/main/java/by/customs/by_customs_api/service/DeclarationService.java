package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.DeclarationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeclarationService {

    DeclarationDto createDeclaration(DeclarationDto dto);

    DeclarationDto getDeclarationById(Long id);

    Page<DeclarationDto> getAllDeclarations(Pageable pageable);

    DeclarationDto updateDeclaration(Long id, DeclarationDto dto);

    void deleteDeclaration(Long id);
}
