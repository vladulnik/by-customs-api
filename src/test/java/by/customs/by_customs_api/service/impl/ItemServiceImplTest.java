package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.entity.DeclarationEntity;
import by.customs.by_customs_api.entity.ItemEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.ItemMapper;
import by.customs.by_customs_api.repository.DeclarationRepository;
import by.customs.by_customs_api.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceImplTest {

    private ItemRepository itemRepository;
    private DeclarationRepository declarationRepository;
    private ItemMapper itemMapper;
    private ItemServiceImpl service;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        declarationRepository = mock(DeclarationRepository.class);
        itemMapper = mock(ItemMapper.class);
        service = new ItemServiceImpl(itemRepository, declarationRepository, itemMapper);
    }

    @Test
    void create_ShouldSaveAndReturnDto() {
        ItemDto dto = ItemDto.builder().declarationId(10L).build();
        ItemEntity entity = new ItemEntity();
        DeclarationEntity decl = new DeclarationEntity();
        decl.setId(10L);

        when(declarationRepository.findById(10L)).thenReturn(Optional.of(decl));
        when(itemMapper.toEntity(dto)).thenReturn(entity);
        when(itemRepository.save(entity)).thenReturn(entity);
        when(itemMapper.toDto(entity)).thenReturn(dto);

        ItemDto result = service.createItem(dto);

        assertNotNull(result);
        verify(itemRepository).save(entity);
    }

    @Test
    void getById_WhenFound_ReturnsDto() {
        ItemEntity entity = new ItemEntity();
        entity.setId(123L);
        ItemDto dto = ItemDto.builder().id(123L).build();

        when(itemRepository.findById(123L)).thenReturn(Optional.of(entity));
        when(itemMapper.toDto(entity)).thenReturn(dto);

        ItemDto result = service.getItemById(123L);

        assertNotNull(result);
        assertEquals(123L, result.getId());
    }

    @Test
    void getById_WhenNotFound_Throws() {
        when(itemRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getItemById(999L));
    }
}
