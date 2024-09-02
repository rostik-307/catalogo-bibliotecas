package es.cic.curso.vuerest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.vuerest.model.Item;
import es.cic.curso.vuerest.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    Item itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public long crearPersona(String nombre, String apellidos, int annoNacimiento) {
        Item persona = new Item(nombre, apellidos, annoNacimiento);
        itemRepository.save(persona);
        return persona.getId();
    }

    public Item save(Item persona) {
        return itemRepository.save(persona);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
