package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.ParticipantDto;
import by.customs.by_customs_api.service.ParticipantService;
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
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> create(@RequestBody @Valid ParticipantDto dto) {
        ParticipantDto created = participantService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> getById(@PathVariable Long id) {
        ParticipantDto dto = participantService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> getAll() {
        List<ParticipantDto> list = participantService.getAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDto> update(
            @PathVariable Long id,
            @RequestBody @Valid ParticipantDto dto) {
        ParticipantDto updated = participantService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        participantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
