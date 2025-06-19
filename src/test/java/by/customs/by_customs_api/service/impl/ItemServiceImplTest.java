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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Test
    void createItem_WhenDeclarationNotFound_Throws() {
        ItemDto dto = ItemDto.builder().declarationId(11L).build();
        when(declarationRepository.findById(11L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.createItem(dto));
    }

    @Test
    void updateItem_WhenItemNotFound_Throws() {
        ItemDto dto = ItemDto.builder().id(88L).declarationId(33L).build();
        when(itemRepository.findById(88L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.updateItem(88L, dto));
    }

    @Test
    void updateItem_WhenDeclarationNotFound_Throws() {
        ItemDto dto = ItemDto.builder().id(8L).declarationId(99L).build();
        ItemEntity entity = new ItemEntity();
        when(itemRepository.findById(8L)).thenReturn(Optional.of(entity));
        when(declarationRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.updateItem(8L, dto));
    }

    @Test
    void deleteItem_WhenNotFound_Throws() {
        when(itemRepository.existsById(55L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> service.deleteItem(55L));
    }

    @Test
    void getAllItems_EmptyPage_ReturnsEmptyPage() {
        when(itemRepository.findAll(any(Pageable.class)))
                .thenReturn(Page.empty());
        Page<ItemDto> page = service.getAllItems(PageRequest.of(0, 5));
        assertTrue(page.isEmpty());
    }
}
