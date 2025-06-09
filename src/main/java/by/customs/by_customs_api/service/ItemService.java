package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto create(ItemDto dto);
    ItemDto getById(Long id);
    List<ItemDto> getAll();
    ItemDto update(Long id, ItemDto dto);
    void delete(Long id);
}
