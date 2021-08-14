package demo.kafka.ecommerce.order.models;

import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private int quantity;
    private int price;
}
