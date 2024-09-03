-- Crear la tabla ITEM (equivalente a Expediente)
CREATE TABLE ITEM (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255),
    release_year INTEGER,
    category_id BIGINT, -- Referencia a la categoría
    FOREIGN KEY (category_id) REFERENCES CATEGORY(id) ON DELETE SET NULL
);

-- Crear la tabla CATEGORY (equivalente a Documento)
CREATE TABLE CATEGORY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255)
);
