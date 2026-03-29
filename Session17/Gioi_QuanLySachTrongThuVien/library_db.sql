-- Database: library_db

CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    published_year INT NOT NULL, -- MySQL YEAR type can be used, but INT is safer for generic JDBC
    price DECIMAL(10,2) NOT NULL
);

-- Note: No procedures requested here, just standard JDBC, 
-- though I'll provide procedures if the goal was similar to others.
-- The prompt doesn't explicitly ask for procedures, just CRUD.
-- Let's stick to standard SQL as implied by "thao tác với cơ sở dữ liệu MySQL" 
-- but I'll use PreparedStatement for security.
