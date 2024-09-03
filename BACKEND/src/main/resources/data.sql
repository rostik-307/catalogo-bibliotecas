-- Insertar datos de ejemplo en la tabla ITEM (El ID se genera automáticamente)
INSERT INTO ITEM (name, details, release_year) VALUES ('Thriller', 'Álbum de Michael Jackson', 1982);
INSERT INTO ITEM (name, details, release_year) VALUES ('The Dark Side of the Moon', 'Álbum de Pink Floyd', 1973);
INSERT INTO ITEM (name, details, release_year) VALUES ('Star Wars', 'Película de George Lucas', 1977);
INSERT INTO ITEM (name, details, release_year) VALUES ('The Godfather', 'Película de Francis Ford Coppola', 1972);
INSERT INTO ITEM (name, details, release_year) VALUES ('Cien Años de Soledad', 'Una novela de Gabriel García Márquez', 1967);

-- Insertar datos de ejemplo en la tabla CATEGORY (El ID se genera automáticamente)
-- Aquí, item_id hace referencia al ID del ITEM al que esta categoría está asociada.
INSERT INTO CATEGORY (name, details, item_id) VALUES ('CD', 'Discos compactos de música', 1);
INSERT INTO CATEGORY (name, details, item_id) VALUES ('VHS', 'Cintas de video VHS', 2);
INSERT INTO CATEGORY (name, details, item_id) VALUES ('Libro', 'Libros de diferentes géneros', 3);
INSERT INTO CATEGORY (name, details, item_id) VALUES ('Revista', 'Revistas de diferentes temas', 4);
