package demo.kafka.ecommerce.order.models;

import lombok.Data;

@Data
public class OrderDTO {
    private String productName;
    private int quantity;
    private String userName;
}
