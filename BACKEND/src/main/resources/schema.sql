-- Crear la tabla CATEGORY primero
CREATE TABLE CATEGORY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255)
);

-- Luego crear la tabla ITEM, que tiene una clave foránea hacia CATEGORY
CREATE TABLE ITEM (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255),
    release_year INTEGER,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES CATEGORY(id) ON DELETE SET NULL
);