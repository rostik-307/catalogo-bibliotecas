package es.cic.curso.vuerest.service;

import es.cic.curso.vuerest.dto.ItemDTO;
import es.cic.curso.vuerest.model.Category;
import es.cic.curso.vuerest.model.Item;
import es.cic.curso.vuerest.repository.CategoryRepository;
import es.cic.curso.vuerest.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ItemDTO crearItem(ItemDTO itemDTO) {
        // Convertir DTO a entidad
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDetails(itemDTO.getDetails());
        item.setReleaseYear(itemDTO.getReleaseYear());

        // Asociar la categoría
        Category category = categoryRepository.findById(itemDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        item.setCategory(category);

        // Guardar la entidad
        Item itemGuardado = itemRepository.save(item);

        // Convertir entidad guardada a DTO y devolver
        return convertirEntidadADTO(itemGuardado);
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDTO convertirEntidadADTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDetails(item.getDetails());
        itemDTO.setReleaseYear(item.getReleaseYear());
        itemDTO.setCategoryId(item.getCategory().getId());
        itemDTO.setCategoryName(item.getCategory().getName());
        return itemDTO;
    }
}