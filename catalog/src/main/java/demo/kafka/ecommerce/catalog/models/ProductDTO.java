package demo.kafka.ecommerce.catalog.models;

import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private int quantity;
    private int price;
}
