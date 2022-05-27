package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.converter.OrderRequestDtoConverter;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.request.OrderRequestDto;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.response.OrderModelAssembler;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.service.OrderServiceInt;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderController implements OrderControllerInt {
    private final OrderServiceInt orderServiceInt;
    private final OrderRequestDtoConverter orderRequestDtoConverter;
    private final OrderModelAssembler modelAssembler;

    public OrderController(OrderServiceInt orderServiceInt, OrderRequestDtoConverter orderRequestDtoConverter, OrderModelAssembler modelAssembler) {
        this.orderServiceInt = orderServiceInt;
        this.orderRequestDtoConverter = orderRequestDtoConverter;
        this.modelAssembler = modelAssembler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Order> createOrder(@Valid @RequestBody OrderRequestDto dto) {
        Order orderCreated = orderRequestDtoConverter.toOrder(dto);
        EntityModel<Order> entityModel = modelAssembler.toModel(orderServiceInt.save(orderCreated));
        return entityModel;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Order> findOrder(@NonNull @PathVariable(name = "id") Long id) {
        Order order = orderServiceInt.getById(id);
        return modelAssembler.toModel(order);
    }
}
