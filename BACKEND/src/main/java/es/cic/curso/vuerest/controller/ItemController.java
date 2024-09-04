package es.cic.curso.vuerest.controller;

import java.util.List;
import java.util.Optional;

import es.cic.curso.vuerest.dto.ItemDTO;
import es.cic.curso.vuerest.model.Category;
import es.cic.curso.vuerest.model.Item;
import es.cic.curso.vuerest.service.ItemService;
import es.cic.curso.vuerest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    // Obtener un ítem por su ID
    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.findById(id).orElse(null);
    }

    // Obtener todos los ítems con el nombre de la categoría
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        try {
            Item item = new Item();
            item.setName(itemDTO.getName());
            item.setDetails(itemDTO.getDetails());
            item.setReleaseYear(itemDTO.getReleaseYear());

            if (itemDTO.getCategoryId() != null) {
                // Busca la categoría por ID
                Category category = categoryService.findById(itemDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
                item.setCategory(category); // Asocia la categoría al ítem
            }

            // Guarda el ítem con la categoría asociada
            Item savedItem = itemService.save(item);

            // Convierte el ítem guardado a DTO para devolverlo
            return ResponseEntity.ok(ItemDTO.convertirEntidadADTO(savedItem));
        } catch (Exception e) {
            // Aquí puedes capturar y manejar la excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el ítem: " + e.getMessage());
        }
    }

    @PutMapping
    public Item updateItem(@RequestBody Item itemDetails) {
        Long id = itemDetails.getId();
        return itemService.findById(id).map(item -> {
            item.setName(itemDetails.getName());
            item.setDetails(itemDetails.getDetails());
            item.setReleaseYear(itemDetails.getReleaseYear());
            return itemService.save(item);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
    }

    @PostMapping("/{id}/associate-category")
    public Item associateCategory(@PathVariable Long id, @RequestBody Long categoryId) {
        // Encontrar el ítem por su ID
        Optional<Item> optionalItem = itemService.findById(id);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            // Encontrar la categoría por su ID
            Category category = categoryService.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            // Asociar la categoría al ítem
            item.setCategory(category);
            return itemService.save(item);
        } else {
            return null;
        }
    }

    private ItemDTO convertToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDetails(item.getDetails());
        itemDTO.setReleaseYear(item.getReleaseYear());

        // Si el ítem tiene una categoría asociada, agregamos el ID de la categoría
        if (item.getCategory() != null) {
            itemDTO.setCategoryId(item.getCategory().getId());
        }

        return itemDTO;
    }

}