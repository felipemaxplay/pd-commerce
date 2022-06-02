package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.listener;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.converter.OrderConverter;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.event.OrderEvent;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.response.OrderEmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEmailQueue implements ApplicationListener<OrderEvent> {
    private static final Logger logger = LoggerFactory.getLogger(OrderEmailQueue.class);

    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;

    public OrderEmailQueue(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onApplicationEvent(OrderEvent event) {
        try {
            OrderEmailDto orderEmailDto = OrderConverter.toOrderEmailDto(event.getOrder());
            String json = objectMapper.writeValueAsString(orderEmailDto);
            jmsTemplate.convertAndSend("order.email.queue", json);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
