package es.cic.curso.vuerest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.vuerest.model.Item;
import es.cic.curso.vuerest.service.ItemService;
import main.java.es.cic.curso.vuerest.dto.ItemDTO;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Obtener un ítem por su ID
    @GetMapping("/{id}")
    public ItemDTO getItem(@PathVariable Long id) {
        Optional<Item> itemOptional = itemService.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            return new ItemDTO(item.getId(), item.getName(), item.getDetails(), item.getReleaseYear(),
                    item.getCategory().getName());
        }
        return null; // Si no se encuentra el ítem
    }

    // Obtener todos los ítems con el nombre de la categoría
    @GetMapping
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemService.findAll();
        return items.stream()
                .map(item -> new ItemDTO(item.getId(), item.getName(), item.getDetails(), item.getReleaseYear(),
                        item.getCategory().getName()))
                .collect(Collectors.toList());
    }

    // Crear un nuevo ítem
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    // Eliminar un ítem por su ID
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        return itemService.findById(id).map(item -> {
            item.setName(itemDetails.getName());
            item.setDetails(itemDetails.getDetails());
            item.setReleaseYear(itemDetails.getReleaseYear());
            item.setCategory(itemDetails.getCategory());
            return itemService.save(item);
        }).orElse(null);
    }
}