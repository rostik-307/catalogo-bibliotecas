package es.cic.curso.vuerest;

import es.cic.curso.vuerest.dto.ItemDTO;
import es.cic.curso.vuerest.model.Category;
import es.cic.curso.vuerest.model.Item;
import es.cic.curso.vuerest.repository.CategoryRepository;
import es.cic.curso.vuerest.repository.ItemRepository;
import es.cic.curso.vuerest.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearItem() {
        // Crear un ItemDTO de prueba
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("Test Item");
        itemDTO.setDetails("Test Details");
        itemDTO.setReleaseYear(2023);
        itemDTO.setCategoryId(1L);

        // Crear una categoría de prueba
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        // Configurar el comportamiento de los mocks
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(itemRepository.save(any(Item.class))).thenAnswer(invocation -> {
            Item item = invocation.getArgument(0);
            item.setId(1L); // Simular que el ítem ha sido guardado con ID 1
            return item;
        });

        // Llamar al método que queremos probar
        ItemDTO result = itemService.crearItem(itemDTO);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals("Test Item", result.getName());
        assertEquals("Test Details", result.getDetails());
        assertEquals(2023, result.getReleaseYear());
        assertEquals(1L, result.getCategoryId());
        assertEquals("Test Category", result.getCategoryName());
    }
}