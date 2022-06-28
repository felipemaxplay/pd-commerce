package br.com.felipemaxplay.pdcommerce.pdproductsservice.listener;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.event.ProductEvent;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductQueueListener implements ApplicationListener<ProductEvent> {

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    private static final Logger logger = LoggerFactory.getLogger(ProductQueueListener.class);

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public ProductQueueListener(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void onApplicationEvent(ProductEvent event) {
        try {
            Product product = event.getProduct();
            String json = objectMapper.writeValueAsString(product);
            rabbitTemplate.convertAndSend(queue, json);
        } catch (JsonProcessingException e) {
            logger.error("Could not convert product object to JSON");
        }
    }
}
