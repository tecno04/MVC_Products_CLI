package CLI_Products_Optional.intefaces;

import CLI_Products_Optional.excpetions.InvalidProductException;
import CLI_Products_Optional.excpetions.ProductNotFoundException;
import CLI_Products_Optional.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll() throws InvalidProductException;
    Optional<Product> findById(Long id);
    void  save(Product product) throws InvalidProductException;
    void  delete(Product product);
    void delete(Long id);
    void update(Optional<Product> product) throws ProductNotFoundException;
    boolean existsById(Long id);

}
