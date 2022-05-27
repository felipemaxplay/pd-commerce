package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.converter.OrderRequestDtoConverter;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.request.OrderRequestDto;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.response.OrderModelAssembler;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.service.OrderServiceInt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
    private final PagedResourcesAssembler pagedResourcesAssembler;

    public OrderController(OrderServiceInt orderServiceInt, OrderRequestDtoConverter orderRequestDtoConverter, OrderModelAssembler modelAssembler, PagedResourcesAssembler pagedResourcesAssembler) {
        this.orderServiceInt = orderServiceInt;
        this.orderRequestDtoConverter = orderRequestDtoConverter;
        this.modelAssembler = modelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<EntityModel<Order>> findAllPaged(@NonNull @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Order> page = orderServiceInt.findAll(pageable);
        PagedModel<EntityModel<Order>> pagedModel = pagedResourcesAssembler.toModel(page, modelAssembler);
        return pagedModel;
    }
}
