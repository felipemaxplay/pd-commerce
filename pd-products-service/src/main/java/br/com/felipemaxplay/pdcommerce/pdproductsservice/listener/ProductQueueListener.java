package br.com.felipemaxplay.pdcommerce.pdproductsservice.listener;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.event.ProductEvent;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductQueueListener implements ApplicationListener<ProductEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ProductQueueListener.class);

    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;

    public ProductQueueListener(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onApplicationEvent(ProductEvent event) {
        try {
            Product product = event.getProduct();
            String json = objectMapper.writeValueAsString(product);
            jmsTemplate.convertAndSend("product.queue", json);
        } catch (JsonProcessingException e) {
            logger.error("Could not convert product object to JSON");
        }
    }
}
