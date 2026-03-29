package ra.dao;

import ra.database.PostgreSQLConnector;
import ra.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAll() {
        Connection conn = PostgreSQLConnector.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("{call get_all_products()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
        } finally {
            PostgreSQLConnector.closeConnection(conn);
        }
        return products;
    }

    public Product getById(int id) {
        Connection conn = PostgreSQLConnector.getConnection();
        Product product = null;
        try {
            CallableStatement cs = conn.prepareCall("{call get_product_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                product = extractProductFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product by ID: " + e.getMessage());
        } finally {
            PostgreSQLConnector.closeConnection(conn);
        }
        return product;
    }

    public boolean checkCatalog(String catalogName) {
        Connection conn = PostgreSQLConnector.getConnection();
        boolean exists = false;
        try {
            // In PostgreSQL, functions returning boolean can be called like this:
            CallableStatement cs = conn.prepareCall("{? = call check_catalog_exists(?)}");
            cs.registerOutParameter(1, Types.BOOLEAN);
            cs.setString(2, catalogName);
            cs.execute();
            exists = cs.getBoolean(1);
        } catch (SQLException e) {
            System.err.println("Error checking catalog: " + e.getMessage());
        } finally {
            PostgreSQLConnector.closeConnection(conn);
        }
        return exists;
    }

    public boolean add(Product p) {
        Connection conn = PostgreSQLConnector.getConnection();
        boolean success = false;
        try {
            conn.setAutoCommit(false); // Transactions
            CallableStatement cs = conn.prepareCall("{call add_product(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, p.getProductName());
            cs.setDouble(2, p.getProductPrice());
            cs.setString(3, p.getProductTitle());
            cs.setDate(4, Date.valueOf(p.getProductCreated()));
            cs.setString(5, p.getProductCatalog());
            cs.setObject(6, p.isProductStatus() ? "1" : "0", Types.BIT);
            cs.execute();
            conn.commit();
            success = true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.err.println("Error adding product: " + e.getMessage());
        } finally {
            PostgreSQLConnector.closeConnection(conn);
        }
        return success;
    }

    public boolean update(Product p) {
        Connection conn = PostgreSQLConnector.getConnection();
        boolean success = false;
        try {
            conn.setAutoCommit(false);
            CallableStatement cs = conn.prepareCall("{call update_product(?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, p.getProductId());
            cs.setString(2, p.getProductName());
            cs.setDouble(3, p.getProductPrice());
            cs.setString(4, p.getProductTitle());
            cs.setDate(5, Date.valueOf(p.getProductCreated()));
            cs.setString(6, p.getProductCatalog());
            cs.setObject(7, p.isProductStatus() ? "1" : "0", Types.BIT);
            cs.execute();
            conn.commit();
            success = true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.err.println("Error updating product: " + e.getMessage());
        } finally {
            PostgreSQLConnector.closeConnection(conn);
        }
        return success;
    }

    public boolean delete(int productId) {
        Connection conn = PostgreSQLConnector.getConnection();
        boolean success = false;
        try {
            conn.setAutoCommit(false);
            CallableStatement cs = conn.prepareCall("{call delete_product(?)}");
            cs.setInt(1, productId);
            cs.execute();
            conn.commit();
            success = true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.err.println("Error deleting product: " + e.getMessage());
        } finally {
            PostgreSQLConnector.closeConnection(conn);
        }
        return success;
    }

    public List<Product> searchByName(String query) {
        Connection conn = PostgreSQLConnector.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("{call search_product_by_name(?)}");
            cs.setString(1, query);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error searching products: " + e.getMessage());
        } finally {
            PostgreSQLConnector.closeConnection(conn);
        }
        return products;
    }

    public void displayStatistics() {
        Connection conn = PostgreSQLConnector.getConnection();
        try {
            CallableStatement cs = conn.prepareCall("{call get_statistics_by_catalog()}");
            ResultSet rs = cs.executeQuery();
            System.out.println("--- PRODUCT STATISTICS ---");
            System.out.println(String.format("%-20s | %-15s", "Catalog", "Quantity"));
            System.out.println("------------------------------------------");
            while (rs.next()) {
                System.out.println(String.format("%-20s | %-15d", rs.getString("catalog_name"), rs.getLong("product_count")));
            }
            System.out.println("------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error getting statistics: " + e.getMessage());
        } finally {
            PostgreSQLConnector.closeConnection(conn);
        }
    }

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt("Product_Id"));
        p.setProductName(rs.getString("Product_Name"));
        p.setProductPrice(rs.getFloat("Product_Price"));
        p.setProductTitle(rs.getString("Product_Title"));
        p.setProductCreated(rs.getDate("Product_created").toLocalDate());
        p.setProductCatalog(rs.getString("Product_catalog"));
        p.setProductStatus(rs.getString("Product_Status").equals("1"));
        return p;
    }
}
