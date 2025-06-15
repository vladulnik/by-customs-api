package by.customs.by_customs_api.service.impl;

import by.customs.by_customs_api.dto.ItemDto;
import by.customs.by_customs_api.entity.ItemEntity;
import by.customs.by_customs_api.exception.exceptions.ResourceNotFoundException;
import by.customs.by_customs_api.mapper.ItemMapper;
import by.customs.by_customs_api.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceImplTest {

    @Mock
    private ItemRepository repository;
    @Mock
    private ItemMapper mapper;

    @InjectMocks
    private ItemServiceImpl service;

    public ItemServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldSaveAndReturnDto() {
        ItemDto dto = ItemDto.builder().build();
        ItemEntity entity = new ItemEntity();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        ItemDto result = service.createItem(dto);

        assertNotNull(result);
        verify(repository).save(entity);
    }

    @Test
    void getById_WhenFound_ReturnsDto() {
        ItemEntity entity = new ItemEntity();
        entity.setId(123L);
        ItemDto dto = ItemDto.builder().id(123L).build();

        when(repository.findById(123L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        ItemDto result = service.getItemById(123L);

        assertNotNull(result);
        assertEquals(123L, result.getId());
    }

    @Test
    void getById_WhenNotFound_Throws() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getItemById(999L));
    }
}
