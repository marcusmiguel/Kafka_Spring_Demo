package demo.kafka.ecommerce.order.services;

import demo.kafka.ecommerce.order.models.OrderDTO;
import demo.kafka.ecommerce.order.models.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OrderService {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    @Value("${topic.name}")
    private String topic;

    @Synchronized
    public void sendOrder(OrderDTO orderDTO){
        kafkaTemplate.send(topic, orderDTO).addCallback(
                success -> log.info("Message sent" + success.getProducerRecord().value()),
                failure -> log.info("Message failure" + failure.getMessage())
        );
    }
}
