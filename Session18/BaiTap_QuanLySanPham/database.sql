-- 1. Create database
-- CREATE DATABASE ProductManagement;

-- 2. Create Product table
CREATE TABLE Product (
    Product_Id SERIAL PRIMARY KEY,
    Product_Name VARCHAR(100) NOT NULL UNIQUE,
    Product_Price FLOAT NOT NULL CHECK (Product_Price > 0),
    Product_Title VARCHAR(200) NOT NULL,
    Product_created DATE NOT NULL,
    Product_catalog VARCHAR(100) NOT NULL,
    Product_Status BIT DEFAULT B'1'
);

-- 3. Store Procedures (PostgreSQL Functions / Procedures)

-- Lấy tất cả thông tin sản phẩm
CREATE OR REPLACE FUNCTION get_all_products()
RETURNS SETOF Product AS $$
BEGIN
    RETURN QUERY SELECT * FROM Product;
END;
$$ LANGUAGE plpgsql;

-- Kiểm tra sự tồn tại của danh mục
CREATE OR REPLACE FUNCTION check_catalog_exists(p_catalog VARCHAR(100))
RETURNS BOOLEAN AS $$
DECLARE
    v_exists BOOLEAN;
BEGIN
    SELECT EXISTS(SELECT 1 FROM Product WHERE Product_catalog = p_catalog) INTO v_exists;
    RETURN v_exists;
END;
$$ LANGUAGE plpgsql;

-- Thêm mới một sản phẩm
CREATE OR REPLACE PROCEDURE add_product(
    p_name VARCHAR(100),
    p_price FLOAT,
    p_title VARCHAR(200),
    p_created DATE,
    p_catalog VARCHAR(100),
    p_status BIT
)
AS $$
BEGIN
    INSERT INTO Product (Product_Name, Product_Price, Product_Title, Product_created, Product_catalog, Product_Status)
    VALUES (p_name, p_price, p_title, p_created, p_catalog, p_status);
END;
$$ LANGUAGE plpgsql;

-- Cập nhật một sản phẩm theo mã sản phẩm
CREATE OR REPLACE PROCEDURE update_product(
    p_id INT,
    p_name VARCHAR(100),
    p_price FLOAT,
    p_title VARCHAR(200),
    p_created DATE,
    p_catalog VARCHAR(100),
    p_status BIT
)
AS $$
BEGIN
    UPDATE Product
    SET Product_Name = p_name,
        Product_Price = p_price,
        Product_Title = p_title,
        Product_created = p_created,
        Product_catalog = p_catalog,
        Product_Status = p_status
    WHERE Product_Id = p_id;
END;
$$ LANGUAGE plpgsql;

-- Xóa một sản phẩm theo mã sản phẩm
CREATE OR REPLACE PROCEDURE delete_product(p_id INT)
AS $$
BEGIN
    DELETE FROM Product WHERE Product_Id = p_id;
END;
$$ LANGUAGE plpgsql;

-- Lấy thông tin sản phẩm theo mã sản phẩm
CREATE OR REPLACE FUNCTION get_product_by_id(p_id INT)
RETURNS SETOF Product AS $$
BEGIN
    RETURN QUERY SELECT * FROM Product WHERE Product_Id = p_id;
END;
$$ LANGUAGE plpgsql;

-- Tìm kiếm sản phẩm theo tên sản phẩm (tương đối)
CREATE OR REPLACE FUNCTION search_product_by_name(p_search VARCHAR(100))
RETURNS SETOF Product AS $$
BEGIN
    RETURN QUERY SELECT * FROM Product WHERE Product_Name ILIKE '%' || p_search || '%';
END;
$$ LANGUAGE plpgsql;

-- Thống kê số lượng sản phẩm theo danh mục
CREATE OR REPLACE FUNCTION get_statistics_by_catalog()
RETURNS TABLE (catalog_name VARCHAR(100), product_count BIGINT) AS $$
BEGIN
    RETURN QUERY SELECT Product_catalog, COUNT(*) FROM Product GROUP BY Product_catalog;
END;
$$ LANGUAGE plpgsql;
