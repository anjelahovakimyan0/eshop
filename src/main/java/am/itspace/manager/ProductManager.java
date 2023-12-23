package am.itspace.manager;

import am.itspace.db.DBConnectionProvider;
import am.itspace.model.Product;

import java.sql.*;

public class ProductManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CategoryManager categoryManager = new CategoryManager();

    public void addProduct(Product product) {
        String sql = "INSERT INTO product(name,description,price,quantity,category_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProductById(Product product) {
        if (getProductById(product.getId()) == null) {
            System.out.println("Product with " + product.getId() + " id does not exists");
            return;
        }
        String sql = "UPDATE product SET name=?,description=?,price=?,quantity=?,category_id=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.setInt(6, product.getId());
            ps.executeUpdate();
            System.out.println("Product has updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductById(int id) {
        if (getProductById(id) == null) {
            System.out.println("Product with " + id + " id does not exists");
            return;
        }
        String sql = "DELETE FROM product WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Product has deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sumOfProducts() {
        String sql = "SELECT SUM(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println(resultSet.getDouble(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void maxOfProducts() {
        String sql = "SELECT MAX(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println(resultSet.getDouble(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void minOfProducts() {
        String sql = "SELECT MIN(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println(resultSet.getDouble(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void avgOfProducts() {
        String sql = "SELECT AVG(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println(resultSet.getDouble(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                int categoryId = resultSet.getInt("category_id");
                return new Product(id, name, description, price, quantity,
                        categoryManager.getCategoryById(categoryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
