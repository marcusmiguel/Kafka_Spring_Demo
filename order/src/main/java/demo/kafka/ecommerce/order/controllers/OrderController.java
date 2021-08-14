package demo.kafka.ecommerce.order.controllers;

import demo.kafka.ecommerce.order.models.OrderDTO;
import demo.kafka.ecommerce.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){
        orderService.sendOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }
}
