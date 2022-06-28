package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.listener;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.converter.OrderConverter;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.event.OrderEvent;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.response.OrderEmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEmailQueue implements ApplicationListener<OrderEvent> {
    @Value("${spring.rabbitmq.queue}")
    private String queue;
    private static final Logger logger = LoggerFactory.getLogger(OrderEmailQueue.class);
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public OrderEmailQueue(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void onApplicationEvent(OrderEvent event) {
        try {
            OrderEmailDto orderEmailDto = OrderConverter.toOrderEmailDto(event.getOrder());
            String json = objectMapper.writeValueAsString(orderEmailDto);
            rabbitTemplate.convertAndSend(queue, json);
        } catch (JsonProcessingException e) {
            logger.error("Could not convert orderEmailDto object to JSON");
        }
    }
}
