package es.cic.curso.vuerest.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso.vuerest.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
     Optional<Category> findById(Long id);
    Category findByName(String name);
}
