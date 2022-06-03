package br.com.felipemaxplay.pdcommerce.pdEmailService.listener;

import br.com.felipemaxplay.pdcommerce.pdEmailService.http.data.response.OrderDto;
import br.com.felipemaxplay.pdcommerce.pdEmailService.model.Email;
import br.com.felipemaxplay.pdcommerce.pdEmailService.model.EmailStatus;
import br.com.felipemaxplay.pdcommerce.pdEmailService.service.EmailServiceInt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailOrderQueue {
    private static final Logger logger = LoggerFactory.getLogger(EmailOrderQueue.class);
    private final ObjectMapper objectMapper;
    private final EmailServiceInt emailServiceInt;

    public EmailOrderQueue(ObjectMapper objectMapper, EmailServiceInt emailServiceInt) {
        this.objectMapper = objectMapper;
        this.emailServiceInt = emailServiceInt;
    }

    @JmsListener(destination = "order.email.queue")
    public void onReceiveTopic(String json) {
        try {
            OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
            String subject = "Successful purchase";
            String content = "OKAY";
            Email email = new Email(orderDto.getId(), orderDto.getEmail(), subject, content, EmailStatus.SENT);
            emailServiceInt.sendEmail(email);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
