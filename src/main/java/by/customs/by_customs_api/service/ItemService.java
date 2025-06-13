package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.ItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    ItemDto createItem(ItemDto dto);

    ItemDto getItemById(Long id);

    Page<ItemDto> getAllItems(Pageable pageable);

    ItemDto updateItem(Long id, ItemDto dto);

    void deleteItem(Long id);
}
