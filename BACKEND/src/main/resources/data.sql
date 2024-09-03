-- Insertar datos de ejemplo en la tabla CATEGORY (El ID se genera automáticamente)
INSERT INTO CATEGORY (name, details) VALUES ('CD', 'Discos compactos de música');
INSERT INTO CATEGORY (name, details) VALUES ('VHS', 'Cintas de video VHS');
INSERT INTO CATEGORY (name, details) VALUES ('Libro', 'Libros de diferentes géneros');
INSERT INTO CATEGORY (name, details) VALUES ('Revista', 'Revistas de diferentes temas');

-- Insertar datos de ejemplo en la tabla ITEM (El ID se genera automáticamente)
-- Aquí, category_id hace referencia al ID de la categoría a la que este ítem pertenece.
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Thriller', 'Álbum de Michael Jackson', 1982, 1);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('The Dark Side of the Moon', 'Álbum de Pink Floyd', 1973, 1);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Star Wars', 'Película de George Lucas', 1977, 2);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('The Godfather', 'Película de Francis Ford Coppola', 1972, 2);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Cien Años de Soledad', 'Una novela de Gabriel García Már', 1900,3);
