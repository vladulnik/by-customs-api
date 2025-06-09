package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ItemEntity;
import by.customs.by_customs_api.exception.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.ItemMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.ItemRepository;
import by.customs.by_customs_api.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public ItemDto create(ItemDto dto) {
        DeclarationEntity decl = declarationRepository.findById(dto.getDeclarationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaration not found with id: " + dto.getDeclarationId()));
        ItemEntity e = itemMapper.toEntity(dto);
        e.setDeclaration(decl);
        return itemMapper.toDto(itemRepository.save(e));
    }

    @Override
    public ItemDto getById(Long id) {
        ItemEntity e = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found: " + id));
        return itemMapper.toDto(e);
    }

    @Override
    public List<ItemDto> getAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto update(Long id, ItemDto dto) {
        ItemEntity existing = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found: " + id));
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
    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item not found: " + id);
        }
        itemRepository.deleteById(id);
    }
}
