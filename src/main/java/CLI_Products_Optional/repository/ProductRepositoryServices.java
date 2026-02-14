package CLI_Products_Optional.repository;

import CLI_Products_Optional.excpetions.InvalidProductException;
import CLI_Products_Optional.excpetions.ProductNotFoundException;
import CLI_Products_Optional.intefaces.ProductRepository;
import CLI_Products_Optional.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductRepositoryServices implements ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() throws InvalidProductException {
        if(products.isEmpty()) {
            throw new InvalidProductException("The list is empty");
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Product product) throws InvalidProductException {
        boolean prd = this.existsById(product.getId());
        if (prd) {
            throw  new InvalidProductException("This product already exist");
        }
        products.add(product);
        System.out.println("Product saved");
    }

    @Override
    public void delete(Product product) {
        List<Product> deleteProductType = products.stream().filter(prodType -> prodType.equals(product)).toList();
        System.out.println("Delete Product: %s".formatted(deleteProductType) );
    }

    @Override
    public void delete(Long id) {
        products.removeIf(product -> product.getId().equals(id));
        System.out.println("Product deleted");
        //List<Product> prod = products.stream().filter(prodDel -> !Objects.equals(prodDel.getId(), id)).toList();
    }

    @Override
    public void update(Optional<Product> product) throws ProductNotFoundException {
        if(product.isPresent()) {
            Long idToUpdate = product.get().getId();
            int indexProduct = findByIndex(idToUpdate);
            if(indexProduct >= 0) {
                products.set(indexProduct, product.get());
                System.out.println("Product %s updated".formatted(product.get().getName()));
            }else{
                System.out.println("This Product is not exist");
            }
        }else{
            System.out.println("This Product is not exist");
        }
    }

    @Override
    public boolean existsById(Long id) {
        //return products.stream().anyMatch(product -> product.getId().equals(id);
        List<Product> prodExist = products.stream().filter(prod -> Objects.equals(prod.getId(), id)).toList();
        return !prodExist.isEmpty();
    }

    private int findByIndex(Long id){
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getId(), id)) {
                return i;
            }
        }
        return -1;
    }
}
