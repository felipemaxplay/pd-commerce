package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.listener;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Product;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductQueue {
    private static final Logger logger = LoggerFactory.getLogger(ProductQueue.class);
    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;

    public ProductQueue(ObjectMapper objectMapper, ProductRepository productRepository) {
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
    }

    @RabbitListener(queues = "product.queue")
    public void onReceiveTopic(String json) {
        try {
            Product product = objectMapper.readValue(json, Product.class);
            productRepository.save(product);
        } catch (JsonProcessingException e) {
            logger.error("Could not create new product instance based on received json", e);
        }
    }
}
