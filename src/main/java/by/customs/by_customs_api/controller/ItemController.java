package by.customs.by_customs_api.controller;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService svc;

    public ItemController(ItemService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<ItemDto> create(@RequestBody ItemDto dto) {
        return ResponseEntity.ok(svc.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAll() {
        return ResponseEntity.ok(svc.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> update(@PathVariable Long id,
                                          @RequestBody ItemDto dto) {
        return ResponseEntity.ok(svc.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
