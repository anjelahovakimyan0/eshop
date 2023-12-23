package am.itspace.model;

public interface Command {

    String EXIT = "0";
    String ADD_CATEGORY = "1";
    String EDIT_CATEGORY_BY_ID = "2";
    String DELETE_CATEGORY_BY_ID = "3";
    String ADD_PRODUCT = "4";
    String EDIT_PRODUCT_BY_ID = "5";
    String DELETE_PRODUCT_BY_ID = "6";
    String PRINT_SUM_OF_PRODUCTS = "7";
    String PRINT_MAX_OF_PRODUCTS = "8";
    String PRINT_MIN_OF_PRODUCTS = "9";
    String PRINT_AVG_OF_PRODUCTS = "10";

    static void printCommands() {
        System.out.println("Please input " + EXIT + " for exit");
        System.out.println("Please input " + ADD_CATEGORY + " for add category");
        System.out.println("Please input " + EDIT_CATEGORY_BY_ID + " for edit category by id");
        System.out.println("Please input " + DELETE_CATEGORY_BY_ID + " for delete category by id");
        System.out.println("Please input " + ADD_PRODUCT + " for add product");
        System.out.println("Please input " + EDIT_PRODUCT_BY_ID + " for edit product by id");
        System.out.println("Please input " + DELETE_PRODUCT_BY_ID + " for delete product by id");
        System.out.println("Please input " + PRINT_SUM_OF_PRODUCTS + " for print sum of products");
        System.out.println("Please input " + PRINT_MAX_OF_PRODUCTS + " for print max of products");
        System.out.println("Please input " + PRINT_MIN_OF_PRODUCTS + " for print min of products");
        System.out.println("Please input " + PRINT_AVG_OF_PRODUCTS + " for print average of products");
    }
}
