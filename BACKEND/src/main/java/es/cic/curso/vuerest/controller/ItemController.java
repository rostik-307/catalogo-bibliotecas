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
import main.java.es.cic.curso.vuerest.service.CategoryService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ItemDTO getItem(@PathVariable Long id) {
        return itemService.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        Item item = convertToEntity(itemDTO);
        Item savedItem = itemService.save(item);
        return convertToDTO(savedItem);
    }

    @PutMapping
    public ItemDTO updateItem(@RequestBody ItemDTO itemDTO) {
        Long id = itemDTO.getId();
        return itemService.findById(id).map(item -> {
            item.setName(itemDTO.getName());
            item.setDetails(itemDTO.getDetails());
            item.setReleaseYear(itemDTO.getReleaseYear());
            if (itemDTO.getCategory() != null) {
                item.setCategory(categoryService.findById(itemDTO.getCategory().getId()));
            }
            Item updatedItem = itemService.save(item);
            return convertToDTO(updatedItem);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
    }

    @PostMapping("/{id}/associate-category")
    public ItemDTO associateCategory(@PathVariable Long id, @RequestBody Long categoryId) {
        Optional<Item> optionalItem = itemService.findById(id);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setCategory(categoryService.findById(categoryId));
            Item updatedItem = itemService.save(item);
            return convertToDTO(updatedItem);
        } else {
            return null;
        }
    }

    private ItemDTO convertToDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDetails(item.getDetails());
        dto.setReleaseYear(item.getReleaseYear());
        if (item.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(item.getCategory().getId());
            categoryDTO.setName(item.getCategory().getName());
            dto.setCategory(categoryDTO);
        }
        return dto;
    }

    private Item convertToEntity(ItemDTO dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDetails(dto.getDetails());
        item.setReleaseYear(dto.getReleaseYear());
        if (dto.getCategory() != null) {
            item.setCategory(categoryService.findById(dto.getCategory().getId()));
        }
        return item;
    }
}