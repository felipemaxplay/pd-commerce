package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.service;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface OrderServiceInt {
    Order save(Order order);

    Order getById(Long id);

    Page<Order> findAll(Pageable pageable);
}
