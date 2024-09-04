package es.cic.curso.vuerest.controller;

import java.util.List;
import java.util.Optional;

import es.cic.curso.vuerest.dto.ItemDTO;
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
import es.cic.curso.vuerest.service.CategoryService;

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

    // Crear un nuevo ítem
    @PostMapping
    public ItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        Item item = convertToEntity(itemDTO);
        Item savedItem = itemService.save(item);
        return convertToDTO(savedItem);
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
    public void deleteitem(@PathVariable Long id) {
        itemService.deleteById(id);
    }

    @PostMapping("/{id}/associate-category")
    public Item associateCategory(@PathVariable Long id, @RequestBody Long categoryId) {
        // Encontrar el ítem por su ID
        Optional<Item> optionalItem = itemService.findById(id);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();

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
        itemDTO.setCategory(item.getCategory().getName());
        return itemDTO;
    }

    private Item convertToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setDetails(itemDTO.getDetails());
        item.setReleaseYear(itemDTO.getReleaseYear());
        item.setCategory(categoryService.findByName(itemDTO.getCategory()));
        return item;
    }

    

}
