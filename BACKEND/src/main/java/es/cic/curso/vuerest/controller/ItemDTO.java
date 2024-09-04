package es.cic.curso.vuerest.;
public class ItemDTO {

}

public class ItemDTO {
    private Long id;
    private String name;
    private String details;
    private int releaseYear;
    private String categoryName; // Campo para el nombre de la categoría

    public ItemDTO(Long id, String name, String details, int releaseYear, String categoryName) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.releaseYear = releaseYear;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
