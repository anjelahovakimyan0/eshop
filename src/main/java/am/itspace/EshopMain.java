package am.itspace;

import am.itspace.manager.CategoryManager;
import am.itspace.manager.ProductManager;
import am.itspace.model.Category;
import am.itspace.model.Command;
import am.itspace.model.Product;

import java.util.Scanner;

public class EshopMain implements Command {

    private static CategoryManager categoryManager = new CategoryManager();
    private static ProductManager productManager = new ProductManager();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;

        while (isRun) {
            Command.printCommands();
            String command = SCANNER.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY_BY_ID:
                    editCategoryById();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategoryById();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProductById();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProductById();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    productManager.sumOfProducts();
                    break;
                case PRINT_MAX_OF_PRODUCTS:
                    productManager.maxOfProducts();
                    break;
                case PRINT_MIN_OF_PRODUCTS:
                    productManager.minOfProducts();
                    break;
                case PRINT_AVG_OF_PRODUCTS:
                    productManager.avgOfProducts();
                    break;
                default:
                    System.out.println("Wrong command.");
                    break;
            }
        }
    }

    private static void addCategory() {
        System.out.println("Please input category name");
        String name = SCANNER.nextLine();
        categoryManager.addCategory(new Category(name));
    }

    private static void editCategoryById() {
        System.out.println("Please input category id");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Please input category name");
        String name = SCANNER.nextLine();
        categoryManager.editCategoryById(new Category(id, name));
    }

    private static void deleteCategoryById() {
        System.out.println("Please input category id");
        int id = Integer.parseInt(SCANNER.nextLine());
        categoryManager.deleteCategoryById(id);
    }

    private static void addProduct() {
        System.out.println("Please input product name");
        String name = SCANNER.nextLine();
        System.out.println("Please input product description");
        String description = SCANNER.nextLine();
        System.out.println("Please input product price");
        double price = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Please input product quantity");
        int quantity = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Please input product's category id");
        int categoryId = Integer.parseInt(SCANNER.nextLine());
        if (categoryManager.getCategoryById(categoryId) == null) {
            System.out.println("Category with " + categoryId + " id does not exists");
            return;
        }
        Product product = new Product(name, description, price, quantity,
                categoryManager.getCategoryById(categoryId));
        productManager.addProduct(product);
    }

    private static void editProductById() {
        System.out.println("Please input product id");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Please input product name");
        String name = SCANNER.nextLine();
        System.out.println("Please input product description");
        String description = SCANNER.nextLine();
        System.out.println("Please input product price");
        double price = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Please input product quantity");
        int quantity = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Please input product's category id");
        int categoryId = Integer.parseInt(SCANNER.nextLine());
        if (categoryManager.getCategoryById(categoryId) == null) {
            System.out.println("Category with " + categoryId + " id does not exists");
            return;
        }
        Product product = new Product(id, name, description, price, quantity,
                categoryManager.getCategoryById(categoryId));
        productManager.editProductById(product);
    }

    private static void deleteProductById() {
        System.out.println("Please input product id");
        int id = Integer.parseInt(SCANNER.nextLine());
        productManager.deleteProductById(id);
    }
}