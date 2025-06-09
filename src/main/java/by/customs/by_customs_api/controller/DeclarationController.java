package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.service.DeclarationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/declarations")
public class DeclarationController {

    private final DeclarationService declarationService;

    public DeclarationController(DeclarationService declarationService) {
        this.declarationService = declarationService;
    }

    @PostMapping
    public ResponseEntity<DeclarationDto> create(@RequestBody @Valid DeclarationDto dto) {
        DeclarationDto created = declarationService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeclarationDto> getById(@PathVariable Long id) {
        DeclarationDto dto = declarationService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DeclarationDto>> getAll() {
        List<DeclarationDto> list = declarationService.getAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeclarationDto> update(
            @PathVariable Long id,
            @RequestBody @Valid DeclarationDto dto) {
        DeclarationDto updated = declarationService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        declarationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
