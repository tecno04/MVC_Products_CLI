package CLI_Products_Optional.view;

import CLI_Products_Optional.controller.ProductController;
import CLI_Products_Optional.excpetions.InvalidProductException;
import CLI_Products_Optional.excpetions.ProductNotFoundException;
import CLI_Products_Optional.model.Product;
import CLI_Products_Optional.model.ProductCategory;
import CLI_Products_Optional.utils.Validates;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductView {

    private final ProductController productController;
    private final Scanner scanner;

    public ProductView(ProductController productController) {
        this.productController = productController;
        this.scanner = new Scanner(System.in);
    }

    public void ShowMenu() {
        while (true) {
            System.out.println("\nSelect one option: ");
            System.out.println("1. Add Product");
            System.out.println("2. Show Products");
            System.out.println("3. Search Product");
            System.out.println("4. Delete Product By ID");
            System.out.println("5. Update Product By ID");
            System.out.println("6. Exit");
            System.out.println("Option: ");

            //this catch de option selected for user avoid "\n"
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> AddProduct();
                case 2 -> ShowAllProducts();
                case 3 -> findByIdProduct();
                case 4 -> deleteProduct();
                case 5 -> updateProduct();
                case 6 -> {
                    scanner.close();
                    return;
                }
            }
        }
    }

    public void AddProduct() {
        long id = readValidLong("Input ID Product", 0);
        String name = readNoneString("Enter de name of product:");
        String categoryString = readNoneString("Enter category of product:" + "\nELECTRONICS, FOOD, BOOKS, OTHERS").trim().toUpperCase();
        double price = readValidDouble("Input price of Product", 0);
        int stock = readValidInteger("Input stock of Product", 0);
        ProductCategory category = ProductCategory.valueOf(categoryString);
        try {
            Product product = new Product(id, name, price, stock, category);
            productController.addProduct(product);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e) {
            System.out.println("The category is not valid");
        }
    }

    public void ShowAllProducts() {
        try {
            List<Product> products = productController.getProducts();
            products.forEach(this::ShowProduct);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findByIdProduct() {
        long id = readValidLong("Input ID Product", 0);
        try {
            Optional<Product> isProduct = productController.getProductById(id);
//            isProduct.ifPresent(this::ShowProduct);
            if(isProduct.isPresent()) {
                ShowProduct(isProduct.get());
            }else{
                System.out.println("The product does not exist");
            }
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateProduct() {
        long id = readValidLong("Input ID Product", 0);
        try {
            Optional<Product> isProduct = productController.getProductById(id);
            if(isProduct.isPresent()) {
                Product product = isProduct.get();
                System.out.println("\nSelect a field to update: ");
                System.out.println("1. Name");
                System.out.println("2. Price");
                System.out.println("3. Stock");
                System.out.println("4. Category");
                System.out.println("5. ALL");
                System.out.println("6. Exit");

                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1 -> product.setName(readNoneString("Enter new name of product:"));
                    case 2 -> product.setPrice(readValidDouble("Enter new price of product:", 0));
                    case 3 -> product.setStock(readValidInteger("Enter new stock of product:", 0));
                    case 4 -> product.setCategory(ProductCategory.valueOf(scanner.nextLine()));
                    case 5 -> {
                        product.setName(readNoneString("Enter new name of product:"));
                        product.setPrice(readValidDouble("Enter new price of product:", 0));
                        product.setStock(readValidInteger("Enter new stock of product:", 0));
                        product.setCategory(ProductCategory.valueOf(scanner.nextLine()));
                    }
                    case 6 -> {
                        return;
                    }
                }

                productController.updateProduct(product);
            }
        } catch (ProductNotFoundException | InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProduct() {
        long id = readValidLong("Input ID Product", 0);
        try {
            productController.removeProduct(id);
        } catch (ProductNotFoundException | InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ShowProduct(Product product) {
        System.out.println("\nProduct:");
        System.out.println("ID: " + product.getId());
        System.out.println("Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Stock: " + product.getStock());
        System.out.println("Category: " + product.getCategory());
        System.out.println("------------------------");
    }

    private String readNoneString(String message) {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine().trim();
            if (input.length() < 3) {
                System.out.println("The value provided is too small");
            }
        } while (input.length() < 3);
        return input;
    }

    private Long readValidLong(String message, long min) {
        long valid;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                valid = Long.parseLong(input);
                if(valid < min) {
                    System.out.println("The value provided is too small, the minimum is " + min);
                    continue;
                }
            return valid;
            }catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private int readValidInteger(String message, long min) {
        int valid;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                valid = Integer.parseInt(input);
                if(valid < min) {
                    System.out.println("The value provided is too small, the minimum is " + min);
                    continue;
                }
                return valid;
            }catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private double readValidDouble(String message, long min) {
        double valid;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                valid = Double.parseDouble(input);
                if(valid < min) {
                    System.out.println("The value provided is too small, the minimum is " + min);
                    continue;
                }
                return valid;
            }catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

}
