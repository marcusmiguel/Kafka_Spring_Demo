package demo.kafka.ecommerce.catalog.models;

import lombok.Data;

@Data
public class OrderDTO {
    private String productName;
    private int quantity;
    private String userName;
}
