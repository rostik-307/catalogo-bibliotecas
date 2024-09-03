package es.cic.curso.vuerest.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String details;
    private int releaseYear;

    @OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    private List<Category> categorias = new ArrayList<>();

    public Item() {
    }

    public Item(String name, String details, int releaseYear) {
        this.name = name;
        this.details = details;
        this.releaseYear = releaseYear;
    }

    public Item(String name, String details, int releaseYear, List<Category> categorias) {
        this.name = name;
        this.details = details;
        this.releaseYear = releaseYear;
        this.categorias = categorias;
    }

    // Getters y Setters
    public Long getId() {
        return id;
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

    public List<Category> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Category> categorias) {
        this.categorias = categorias;
    }

    public void addCategoria(Category categoria) {
        categorias.add(categoria);
        categoria.setItem(this);
    }

    public void removeCategoria(Category categoria) {
        categorias.remove(categoria);
        categoria.setItem(null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((details == null) ? 0 : details.hashCode());
        result = prime * result + releaseYear;
        result = prime * result + ((categorias == null) ? 0 : categorias.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        if (releaseYear != other.releaseYear)
            return false;
        if (categorias == null) {
            if (other.categorias != null)
                return false;
        } else if (!categorias.equals(other.categorias))
            return false;
        return true;
    }
}
