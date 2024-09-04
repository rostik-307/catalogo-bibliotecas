package es.cic.curso.vuerest.controller;

import es.cic.curso.vuerest.service.CategoryService;
import es.cic.curso.vuerest.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Obtener todas las categorías
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    // Crear una nueva categoría
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    // Actualizar una categoría existente
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryService.findById(id).map(category -> {
            category.setName(categoryDetails.getName());
            return categoryService.save(category);
        }).orElse(null);
    }

    // Eliminar una categoría
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}