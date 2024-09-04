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
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Inception', 'Película de Christopher Nolan', 2010, 1);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('The Matrix', 'Película de las hermanas Wachowski', 1999, 1);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Harry Potter y la Piedra Filosofal', 'Libro de J.K. Rowling', 1997, 3);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('El Quijote', 'Libro de Miguel de Cervantes', 1605, 3);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Back to the Future', 'Película de Robert Zemeckis', 1985, 1);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Avatar', 'Película de James Cameron', 2009, 1);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('1984', 'Libro de George Orwell', 1949, 3);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('The Hobbit', 'Libro de J.R.R. Tolkien', 1937, 3);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('National Geographic', 'Revista de ciencia y naturaleza', 2021, 4);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Time', 'Revista de noticias y actualidad', 2021, 4);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('The Beatles - Abbey Road', 'Álbum de The Beatles', 1969, 2);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Led Zeppelin IV', 'Álbum de Led Zeppelin', 1971, 2);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('The Shawshank Redemption', 'Película de Frank Darabont', 1994, 1);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('Pulp Fiction', 'Película de Quentin Tarantino', 1994, 1);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('The Catcher in the Rye', 'Libro de J.D. Salinger', 1951, 3);
INSERT INTO ITEM (name, details, release_year, category_id) VALUES ('To Kill a Mockingbird', 'Libro de Harper Lee', 1960, 3);
