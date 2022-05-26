package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.service;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderServiceInt {
    Order save(Order order);
}
