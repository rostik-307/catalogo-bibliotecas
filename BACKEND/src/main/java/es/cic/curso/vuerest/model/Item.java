package es.cic.curso.vuerest.model;

import jakarta.persistence.*;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String details;
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    // Constructor predeterminado
    public Item() {
    }

    public Item(String name, String details, int releaseYear) {
        this.name = name;
        this.details = details;
        this.releaseYear = releaseYear;

    }

    // Constructor con parámetros
    public Item(String name, String details, int releaseYear, Category catedgory) {
        this.name = name;
        this.details = details;
        this.releaseYear = releaseYear;
        this.category = category;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Item item = (Item) o;
        return releaseYear == item.releaseYear && Objects.equals(id, item.id) && Objects.equals(name, item.name)
                && Objects.equals(details, item.details) && Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, details, releaseYear, category);
    }

    // toString
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", releaseYear=" + releaseYear +
                ", category=" + category.getName() +
                '}';
    }
}
