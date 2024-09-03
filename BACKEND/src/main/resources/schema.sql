-- Crear la tabla CATEGORY
CREATE TABLE CATEGORY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255)
);

-- Crear la tabla ITEM
CREATE TABLE ITEM (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255),
    release_year INTEGER,
    categoria_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES CATEGORY(id)
);