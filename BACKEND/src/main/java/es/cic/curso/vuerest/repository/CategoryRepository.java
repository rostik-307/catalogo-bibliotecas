package es.cic.curso.vuerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso.vuerest.model.Item;

@Repository
public interface CategoryRepository extends JpaRepository<Item, Long> {

}
