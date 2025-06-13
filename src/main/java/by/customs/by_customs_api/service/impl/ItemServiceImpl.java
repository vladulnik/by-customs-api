package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ItemEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.ItemMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.ItemRepository;
import by.customs.by_customs_api.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final DeclarationRepository declarationRepository;
    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository,
                           DeclarationRepository declarationRepository,
                           ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.declarationRepository = declarationRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemDto createItem(ItemDto dto) {
        DeclarationEntity decl = declarationRepository.findById(dto.getDeclarationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaration not found with id: " + dto.getDeclarationId()));
        ItemEntity entity = itemMapper.toEntity(dto);
        entity.setDeclaration(decl);
        return itemMapper.toDto(itemRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDto getItemById(Long id) {
        ItemEntity entity = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item not found with id: " + id));
        return itemMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemDto> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable)
                .map(itemMapper::toDto);
    }

    @Override
    public ItemDto updateItem(Long id, ItemDto dto) {
        ItemEntity existing = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item not found with id: " + id));
        DeclarationEntity decl = declarationRepository.findById(dto.getDeclarationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaration not found with id: " + dto.getDeclarationId()));

        existing.setHsCode(dto.getHsCode());
        existing.setValue(dto.getValue());
        existing.setWeight(dto.getWeight());
        existing.setOriginCountry(dto.getOriginCountry());
        existing.setDeclaration(decl);

        return itemMapper.toDto(itemRepository.save(existing));
    }

    @Override
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }
}
