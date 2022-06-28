package br.com.felipemaxplay.pdcommerce.pdproductsservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.queue}")
    private String queue;



    @Bean
    public Queue Queue() {
        return new Queue(queue);
    }
}
