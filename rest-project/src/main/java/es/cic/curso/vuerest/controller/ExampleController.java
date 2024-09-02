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

import es.cic.curso.vuerest.model.Example;
import es.cic.curso.vuerest.service.ExampleService;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    private exampleService exampleService;

    @GetMapping("/{id}")
    public Example getexample(@PathVariable Long id) {
        return exampleService.findById(id).orElse(null);
    }

    @GetMapping
    public List<Example> getAllexamplees() {
        return exampleService.findAll();
    }

    @PostMapping
    public Example createexample(@RequestBody Example persona) {
        return exampleService.save(persona);
    }

    @PutMapping
    public Example updateexample(@RequestBody Example personaDetails) {
        Long id = personaDetails.getId();
        return exampleService.findById(id).map(persona -> {
            persona.setNombre(personaDetails.getNombre());
            persona.setApellidos(personaDetails.getApellidos());
            persona.setAnnoNacimiento(personaDetails.getAnnoNacimiento());
            return exampleService.save(persona);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteexample(@PathVariable Long id) {
        exampleService.deleteById(id);
    }
}
