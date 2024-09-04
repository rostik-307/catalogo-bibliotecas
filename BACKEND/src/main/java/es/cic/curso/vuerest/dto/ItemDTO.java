package es.cic.curso.vuerest.dto;

import es.cic.curso.vuerest.model.Item;

public class ItemDTO {

    private Long id;
    private String name;
    private String details;
    private Integer releaseYear;
    private Long categoryId;
    private String categoryName;

    // Getters y setters
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static ItemDTO convertirEntidadADTO(Item item) {
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