-- Crear la tabla AUTOR
CREATE TABLE AUTOR (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellidos VARCHAR(255),
    anno_nacimiento INTEGER
);

-- Crear la tabla LIBRO
CREATE TABLE LIBRO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    isbn VARCHAR(255),
    anno_publicacion INTEGER,
    autor_id BIGINT,
    FOREIGN KEY (autor_id) REFERENCES AUTOR(id)
);

-- Insertar datos de ejemplo en la tabla AUTOR
INSERT INTO AUTOR (nombre, apellidos, anno_nacimiento) VALUES ('Gabriel', 'García Márquez', 1927);
INSERT INTO AUTOR (nombre, apellidos, anno_nacimiento) VALUES ('Isabel', 'Allende', 1942);
INSERT INTO AUTOR (nombre, apellidos, anno_nacimiento) VALUES ('Jorge', 'Luis Borges', 1899);
INSERT INTO AUTOR (nombre, apellidos, anno_nacimiento) VALUES ('Mario', 'Vargas Llosa', 1936);
INSERT INTO AUTOR (nombre, apellidos, anno_nacimiento) VALUES ('Julio', 'Cortázar', 1914);

-- Insertar datos de ejemplo en la tabla LIBRO
INSERT INTO LIBRO (titulo, isbn, anno_publicacion, autor_id) VALUES ('Cien Años de Soledad', '978-3-16-148410-0', 1967, 1);
INSERT INTO LIBRO (titulo, isbn, anno_publicacion, autor_id) VALUES ('La Casa de los Espíritus', '978-0-06-088328-7', 1982, 2);
INSERT INTO LIBRO (titulo, isbn, anno_publicacion, autor_id) VALUES ('Ficciones', '978-0-14-118384-8', 1944, 3);
INSERT INTO LIBRO (titulo, isbn, anno_publicacion, autor_id) VALUES ('La Ciudad y los Perros', '978-0-374-52738-5', 1963, 4);
INSERT INTO LIBRO (titulo, isbn, anno_publicacion, autor_id) VALUES ('Rayuela', '978-0-394-70015-8', 1963, 5);