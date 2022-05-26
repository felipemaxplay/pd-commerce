package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.service.OrderServiceInt;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderController implements OrderControllerInt {
    private final OrderServiceInt orderServiceInt;

    public OrderController(OrderServiceInt orderServiceInt) {
        this.orderServiceInt = orderServiceInt;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody Order order) {
        Order orderCreated = orderServiceInt.save(order);
        return orderCreated;
    }
}
