package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.service.DeclarationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Контроллер для управления декларациями
 */
@RestController
@RequestMapping("/api/declarations")
@RequiredArgsConstructor
public class DeclarationController {

    private final DeclarationService declarationService;

    /**
     * Создать новую декларацию
     */
    @PostMapping
    public ResponseEntity<DeclarationDto> createDeclaration(@Valid @RequestBody DeclarationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(declarationService.createDeclaration(dto));
    }

    /**
     * Получить декларацию по идентификатору
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeclarationDto> getDeclarationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(declarationService.getDeclarationById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Получить список всех деклараций (с пагинацией)
     */
    @GetMapping
    public ResponseEntity<Page<DeclarationDto>> getAllDeclarations(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(declarationService.getAllDeclarations(pageable));
    }

    /**
     * Обновить декларацию по идентификатору
     */
    @PutMapping("/{id}")
    public ResponseEntity<DeclarationDto> updateDeclaration(@PathVariable Long id, @Valid @RequestBody DeclarationDto dto) {
        try {
            return ResponseEntity.ok(declarationService.updateDeclaration(id, dto));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Удалить декларацию по идентификатору
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeclaration(@PathVariable Long id) {
        try {
            declarationService.deleteDeclaration(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
