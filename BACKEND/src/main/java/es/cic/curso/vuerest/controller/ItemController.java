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
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        Optional<Item> itemOptional = itemService.findById(id);
        if (itemOptional.isPresent()) {
            return ResponseEntity.ok(itemOptional.get().toDTO());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Obtener todos los ítems con el nombre de la categoría
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<Item> items = itemService.findAll();
        List<ItemDTO> itemDTOs = items.stream().map(Item::toDTO).toList();
        return ResponseEntity.ok(itemDTOs);
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
            return ResponseEntity.ok(savedItem.toDTO());
        } catch (Exception e) {
            // Aquí puedes capturar y manejar la excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el ítem: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        Optional<Item> itemOptional = itemService.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            item.setName(itemDTO.getName());
            item.setDetails(itemDTO.getDetails());
            item.setReleaseYear(itemDTO.getReleaseYear());

            if (itemDTO.getCategoryId() != null) {
                Category category = categoryService.findById(itemDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
                item.setCategory(category);
            }

            Item updatedItem = itemService.save(item);
            return ResponseEntity.ok(updatedItem.toDTO());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ítem no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el ítem: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/associate-category")
    public ResponseEntity<?> associateCategory(@PathVariable Long id, @RequestBody Long categoryId) {
        Optional<Item> optionalItem = itemService.findById(id);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            Category category = categoryService.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            item.setCategory(category);
            Item updatedItem = itemService.save(item);
            return ResponseEntity.ok(updatedItem.toDTO());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ítem no encontrado");
        }
    }
}