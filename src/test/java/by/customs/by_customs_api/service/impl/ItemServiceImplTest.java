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
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

    private ItemRepository itemRepository;
    private DeclarationRepository declarationRepository;
    private ItemMapper itemMapper;
    private ItemServiceImpl service;

    @BeforeEach
    public void setUp() {
        itemRepository = Mockito.mock(ItemRepository.class);
        declarationRepository = Mockito.mock(DeclarationRepository.class);
        itemMapper = Mappers.getMapper(ItemMapper.class);
        service = new ItemServiceImpl(itemRepository, declarationRepository, itemMapper);
    }

    @Test
    public void createShouldMapAndSave() {
        DeclarationEntity decl = new DeclarationEntity();
        decl.setId(1L);
        Mockito.when(declarationRepository.findById(1L)).thenReturn(Optional.of(decl));

        ItemDto dto = new ItemDto(null, "HS123", 100.0, 2.5, "RU", 1L);
        ItemEntity saved = new ItemEntity(5L, "HS123", 100.0, 2.5, "RU", decl);
        Mockito.when(itemRepository.save(any(ItemEntity.class))).thenReturn(saved);

        ItemDto result = service.create(dto);

        Assertions.assertEquals(5L, result.getId());
        Assertions.assertEquals("HS123", result.getHsCode());
        Assertions.assertEquals(1L, result.getDeclarationId());
        Mockito.verify(itemRepository).save(any(ItemEntity.class));
    }

    @Test
    public void getByIdShouldThrow() {
        Mockito.when(itemRepository.findById(10L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.getById(10L));
    }
}
