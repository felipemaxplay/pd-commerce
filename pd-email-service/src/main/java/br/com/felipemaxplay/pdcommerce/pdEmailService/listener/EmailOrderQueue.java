package br.com.felipemaxplay.pdcommerce.pdEmailService.listener;

import br.com.felipemaxplay.pdcommerce.pdEmailService.http.data.response.OrderDto;
import br.com.felipemaxplay.pdcommerce.pdEmailService.model.Email;
import br.com.felipemaxplay.pdcommerce.pdEmailService.model.EmailStatus;
import br.com.felipemaxplay.pdcommerce.pdEmailService.service.EmailServiceInt;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Component
public class EmailOrderQueue {
    private static final Logger logger = LoggerFactory.getLogger(EmailOrderQueue.class);
    private final ObjectMapper objectMapper;
    private final EmailServiceInt emailServiceInt;
    private final Configuration configFreemarker;

    public EmailOrderQueue(ObjectMapper objectMapper, EmailServiceInt emailServiceInt, Configuration configFreemarker) {
        this.objectMapper = objectMapper;
        this.emailServiceInt = emailServiceInt;
        this.configFreemarker = configFreemarker;
    }

    @RabbitListener(queues = "order.email.queue")
    public void onReceiveTopic(String json) {
        try {
            logger.info("Received message in queue");
            OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
            String subject = "Successful purchase order id: " + orderDto.getId();
            String content = getEmailContent(orderDto);
            Email email = new Email(orderDto.getId(), orderDto.getEmail(), subject, content, EmailStatus.SENT);
            emailServiceInt.sendEmail(email);
        } catch (TemplateException e) {
            logger.error("Unable to generate the content of the email");
        } catch (IOException e) {
            logger.error("Could not create new product instance based on received json");
        }
    }

    private String getEmailContent(OrderDto orderDto) throws IOException, TemplateException {
        ModelMap model = new ModelMap("order", orderDto);
        Template template = configFreemarker.getTemplate("orderEmailContent.ftlh");
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}
