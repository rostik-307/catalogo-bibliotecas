-- Crear la tabla ITEM (equivalente a Expediente)
CREATE TABLE ITEM (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255),
    release_year INTEGER
);

-- Crear la tabla CATEGORY (equivalente a Documento)
CREATE TABLE CATEGORY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255),
    item_id BIGINT,
    FOREIGN KEY (item_id) REFERENCES ITEM(id) ON DELETE CASCADE
);
