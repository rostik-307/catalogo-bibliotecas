-- Crear la tabla CATEGORY
CREATE TABLE CATEGORY (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255)
);

-- Crear la tabla ITEM
CREATE TABLE ITEM (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    details VARCHAR(255),
    release_year INTEGER,
    categoria_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES CATEGORY(id)
);

-- Insertar datos de ejemplo en la tabla CATEGORY
INSERT INTO CATEGORY (id, name, details) VALUES (1, 'CD', 'Discos compactos de música');
INSERT INTO CATEGORY (id, name, details) VALUES (2, 'VHS', 'Cintas de video VHS');
INSERT INTO CATEGORY (id, name, details) VALUES (3, 'Libro', 'Libros de diferentes géneros');
INSERT INTO CATEGORY (id, name, details) VALUES (4, 'Revista', 'Revistas de diferentes temas');

-- Insertar datos de ejemplo en la tabla ITEM
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (1, 'Thriller', 'Álbum de Michael Jackson', 1982, 1);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (2, 'The Dark Side of the Moon', 'Álbum de Pink Floyd', 1973, 1);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (3, 'Star Wars', 'Película de George Lucas', 1977, 2);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (4, 'The Godfather', 'Película de Francis Ford Coppola', 1972, 2);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (5, 'Cien Años de Soledad', 'Una novela de Gabriel García Márquez', 1967, 3);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (6, 'National Geographic', 'Revista de ciencia y naturaleza', 2021, 4);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (7, 'Abbey Road', 'Álbum de The Beatles', 1969, 1);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (8, 'Back in Black', 'Álbum de AC/DC', 1980, 1);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (9, 'Hotel California', 'Álbum de Eagles', 1976, 1);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (10, 'Rumours', 'Álbum de Fleetwood Mac', 1977, 1);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (11, 'The Wall', 'Álbum de Pink Floyd', 1979, 1);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (12, 'Goodfellas', 'Película de Martin Scorsese', 1990, 2);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (13, 'Pulp Fiction', 'Película de Quentin Tarantino', 1994, 2);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (14, 'The Shawshank Redemption', 'Película de Frank Darabont', 1994, 2);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (15, 'Fight Club', 'Película de David Fincher', 1999, 2);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (16, '1984', 'Una novela de George Orwell', 1949, 3);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (17, 'To Kill a Mockingbird', 'Una novela de Harper Lee', 1960, 3);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (18, 'The Great Gatsby', 'Una novela de F. Scott Fitzgerald', 1925, 3);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (19, 'Moby Dick', 'Una novela de Herman Melville', 1851, 3);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (20, 'War and Peace', 'Una novela de Leo Tolstoy', 1869, 3);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (21, 'Time', 'Revista de noticias', 2021, 4);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (22, 'Forbes', 'Revista de negocios', 2021, 4);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (23, 'Scientific American', 'Revista de ciencia', 2021, 4);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (24, 'The Economist', 'Revista de economía', 2021, 4);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (25, 'Nature', 'Revista de ciencia', 2021, 4);
INSERT INTO ITEM (id, name, details, release_year, categoria_id) VALUES (26, 'Rolling Stone', 'Revista de música', 2021, 4);