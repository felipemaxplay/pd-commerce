package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.service;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.event.OrderEvent;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class OrderService implements OrderServiceInt {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderService(OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Order save(Order order) {
        logger.info("Order Saved");
        Order orderCreated = orderRepository.save(order);
        applicationEventPublisher.publishEvent(new OrderEvent(this, orderCreated));
        return orderCreated;
    }

    @Override
    public Order getById(Long id) {
        logger.info("Retrieved order id: " + id);
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("order with id %d not found", id)));
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        logger.info("Retrieved paged orders");
        return orderRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NoResultException(String.format("order with id %d not found", id));
        }
        logger.info("Deleted order id: " + id);
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateById(Long id, Order orderUpdated) {
        Order orderExist = orderRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("order with id %d not found", id)));
        logger.info("updated order id: " + id);
        orderExist.finalizeOrder(orderUpdated.getAddress(), orderUpdated.getEmail(), orderUpdated.getProducts());
        applicationEventPublisher.publishEvent(new OrderEvent(this, orderExist));
        return orderRepository.save(orderExist);
    }
}
