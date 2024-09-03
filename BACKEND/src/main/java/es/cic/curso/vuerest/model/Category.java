package es.cic.curso.vuerest.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String details;

    @OneToMany(mappedBy = "category", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    @JsonManagedReference
    private List<Item> items;

    // Constructor sin parámetros
    public Category() {
    }

    // Constructor con parámetros
    public Category(String name, String details) {
        this.name = name;
        this.details = details;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name)
                && Objects.equals(details, category.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, details);
    }

    // toString
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
