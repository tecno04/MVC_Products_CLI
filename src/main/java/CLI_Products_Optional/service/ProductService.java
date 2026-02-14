package CLI_Products_Optional.service;

import CLI_Products_Optional.excpetions.InvalidProductException;
import CLI_Products_Optional.excpetions.ProductNotFoundException;
import CLI_Products_Optional.intefaces.ProductRepository;
import CLI_Products_Optional.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() throws InvalidProductException {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) throws  InvalidProductException {
        ProductValidator.validate(product);
        productRepository.save(product);
    }

    public void deleteProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.delete(id);
        }else{
            throw  new ProductNotFoundException("The product you have delete not exist");
        }
    }

    public void updateProduct(Product product) throws ProductNotFoundException, InvalidProductException {
        ProductValidator.validate(product);
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if(optionalProduct.isPresent()){
            productRepository.update(optionalProduct);
        }else{
            throw new ProductNotFoundException("The product you have updated not exist");
        }
    }

}
