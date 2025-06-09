package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService svc;

    public ParticipantController(ParticipantService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> create(@RequestBody ParticipantDto dto) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> getAll() {
        return ResponseEntity.ok(svc.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDto> update(@PathVariable Long id,
                                                 @RequestBody ParticipantDto dto) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
