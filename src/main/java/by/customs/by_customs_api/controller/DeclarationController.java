package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.DeclarationDto;
import by.customs.by_customs_api.service.DeclarationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/declarations")
public class DeclarationController {

    private final DeclarationService svc;

    public DeclarationController(DeclarationService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<DeclarationDto> create(@RequestBody DeclarationDto dto) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeclarationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DeclarationDto>> getAll() {
        return ResponseEntity.ok(svc.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeclarationDto> update(@PathVariable Long id,
                                                 @RequestBody DeclarationDto dto) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
