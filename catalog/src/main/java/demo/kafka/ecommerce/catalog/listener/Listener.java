package demo.kafka.ecommerce.catalog.listener;

import demo.kafka.ecommerce.catalog.models.OrderDTO;
import demo.kafka.ecommerce.catalog.services.CatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor (onConstructor = @__ (@Autowired))
public class Listener {
        private final CatalogService catalogService;

        @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "orderKafkaListenerContainerFactory")
        public void listenTopicOrder(ConsumerRecord<String, OrderDTO> record){
            log.info("Received Message " + record.partition());
            log.info("Received Message " + record.value());
            catalogService.dispatchOrder(record.value());
        }
}
