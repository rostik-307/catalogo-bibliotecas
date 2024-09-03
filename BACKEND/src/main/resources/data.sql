-- Insertar datos de ejemplo en la tabla CATEGORY (El ID se genera automáticamente)
INSERT INTO CATEGORY (id, name, details) VALUES (1, 'CD', 'Discos compactos de música');
INSERT INTO CATEGORY (id, name, details) VALUES (2, 'VHS', 'Cintas de video VHS');
INSERT INTO CATEGORY (id, name, details) VALUES (3, 'Libro', 'Libros de diferentes géneros');
INSERT INTO CATEGORY (id, name, details) VALUES (4, 'Revista', 'Revistas de diferentes temas');

-- Insertar datos de ejemplo en la tabla ITEM (El ID se genera automáticamente)
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Thriller', 'Álbum de Michael Jackson', 1982, 2);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('The Dark Side of the Moon', 'Álbum de Pink Floyd', 1973, 2);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Star Wars', 'Película de George Lucas', 1977, 1);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('The Godfather', 'Película de Francis Ford Coppola', 1972, 4);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Cien Años de Soledad', 'Una novela de Gabriel García Márquez', 1967, 3);
