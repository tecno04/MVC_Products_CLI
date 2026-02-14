package CLI_Products_Optional.service;

import CLI_Products_Optional.excpetions.InvalidProductException;
import CLI_Products_Optional.model.Product;

public class ProductValidator {

    public static void validate(Product product) throws InvalidProductException {
        if(product.getPrice() <= 0){
            throw new InvalidProductException("The product has not have price negative");
        }

        if(product.getStock() < 0){
            throw new InvalidProductException("The product has not have stock negative");
        }
    }

}
