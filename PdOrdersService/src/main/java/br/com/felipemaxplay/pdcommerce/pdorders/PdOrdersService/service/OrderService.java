package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.service;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class OrderService implements OrderServiceInt {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        Order orderCreated = orderRepository.save(order);
        return orderCreated;
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("order with id %d not found", id)));
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NoResultException(String.format("order with id %d not found", id));
        }
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateById(Long id, Order orderUpdated) {
        Order orderExist = orderRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("order with id %d not found", id)));
        orderExist.finalizeOrder(orderUpdated.getAddress(), orderUpdated.getEmail(), orderUpdated.getProducts());
        return orderRepository.save(orderExist);
    }
}
