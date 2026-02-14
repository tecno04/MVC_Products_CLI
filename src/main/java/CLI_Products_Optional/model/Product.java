package CLI_Products_Optional.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private Long id;
    private String name;
    private double price;
    private int stock;
    private ProductCategory category;
}
