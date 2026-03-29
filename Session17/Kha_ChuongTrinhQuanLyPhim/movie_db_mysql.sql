-- Database: movie_db

CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    year INT NOT NULL
);

-- Stored procedure to add a movie
DELIMITER //
CREATE PROCEDURE add_movie(
    IN p_title VARCHAR(255),
    IN p_director VARCHAR(255),
    IN p_year INT
)
BEGIN
    INSERT INTO movies (title, director, year)
    VALUES (p_title, p_director, p_year);
END //
DELIMITER ;

-- Stored procedure to list movies
DELIMITER //
CREATE PROCEDURE list_movies()
BEGIN
    SELECT * FROM movies;
END //
DELIMITER ;

-- Stored procedure to update a movie
DELIMITER //
CREATE PROCEDURE update_movie(
    IN p_id INT,
    IN p_title VARCHAR(255),
    IN p_director VARCHAR(255),
    IN p_year INT
)
BEGIN
    UPDATE movies
    SET title = p_title,
        director = p_director,
        year = p_year
    WHERE id = p_id;
END //
DELIMITER ;

-- Stored procedure to delete a movie
DELIMITER //
CREATE PROCEDURE delete_movie(
    IN p_id INT
)
BEGIN
    DELETE FROM movies WHERE id = p_id;
END //
DELIMITER ;
