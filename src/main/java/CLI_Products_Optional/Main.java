package CLI_Products_Optional;

import CLI_Products_Optional.controller.ProductController;
import CLI_Products_Optional.intefaces.ProductRepository;
import CLI_Products_Optional.repository.ProductRepositoryServices;
import CLI_Products_Optional.service.ProductService;
import CLI_Products_Optional.view.ProductView;

public class Main {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepositoryServices();
        ProductService service = new ProductService(repository);
        ProductController controller = new ProductController(service);
        ProductView view = new ProductView(controller);
        view.ShowMenu();
    }
}
