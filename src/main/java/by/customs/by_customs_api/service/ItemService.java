package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.ItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    ItemDto create(ItemDto dto);

    ItemDto getById(Long id);

    Page<ItemDto> getAll(Pageable pageable);

    ItemDto update(Long id, ItemDto dto);

    void delete(Long id);
}
