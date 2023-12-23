package am.itspace.manager;

import am.itspace.db.DBConnectionProvider;
import am.itspace.model.Category;

import java.sql.*;

public class CategoryManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addCategory(Category category) {
        String sql = "INSERT INTO category(name) VALUES(?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                category.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCategoryById(Category category) {
        if (getCategoryById(category.getId()) == null) {
            System.out.println("Category with " + category.getId() + " does not exists");
            return;
        }
        String sql = "UPDATE category SET name=? WHERE id=" + category.getId();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            System.out.println("Category has updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategoryById(int id) {
        if (getCategoryById(id) == null) {
            System.out.println("Category with " + id + " id does not exists");
            return;
        }
        String sql = "DELETE FROM category WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Category has deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM category WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
