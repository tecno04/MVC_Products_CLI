package CLI_Products_Optional.controller;

import CLI_Products_Optional.excpetions.InvalidProductException;
import CLI_Products_Optional.excpetions.ProductNotFoundException;
import CLI_Products_Optional.model.Product;
import CLI_Products_Optional.service.ProductService;
import CLI_Products_Optional.utils.Validates;

import java.util.List;
import java.util.Optional;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(Product product) throws InvalidProductException {
        Validates.validateObject(product, "The product you have provided is invalid");
        productService.saveProduct(product);
    }

    public void removeProduct(Long id) throws ProductNotFoundException, InvalidProductException {
        Validates.validateNumber(id, "The product ID has not be Null or empty");
        productService.deleteProductById(id);
    }

    public List<Product> getProducts() throws InvalidProductException {
        return productService.getAllProducts();
    }

    public Optional<Product> getProductById(Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    public void updateProduct(Product product) throws ProductNotFoundException, InvalidProductException {
        Validates.validateObject(product, "The product you have provided is invalid");
        productService.updateProduct(product);
    }

}
