package es.cic.curso.vuerest.controller;

import java.util.List;

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


@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.findById(id).orElse(null);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.save(item);
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
}
