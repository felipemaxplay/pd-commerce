package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.converter.OrderRequestDtoConverter;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.request.OrderRequestDto;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.response.OrderModelAssembler;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.service.OrderServiceInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
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

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Order> createOrder(@Valid @RequestBody OrderRequestDto dto) {
        logger.info("Creating new order");
        Order orderCreated = orderRequestDtoConverter.toOrder(dto);
        EntityModel<Order> entityModel = modelAssembler.toModel(orderServiceInt.save(orderCreated));
        return entityModel;
    }

    @Override
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Order> findOrder(@NonNull @PathVariable(name = "id") Long id) {
        logger.info("Finding order by id");
        Order order = orderServiceInt.getById(id);
        return modelAssembler.toModel(order);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<EntityModel<Order>> findAllPaged(@NonNull @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        logger.info("Retrieving paged order");
        Page<Order> page = orderServiceInt.findAll(pageable);
        PagedModel<EntityModel<Order>> pagedModel = pagedResourcesAssembler.toModel(page, modelAssembler);
        return pagedModel;
    }

    @Override
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@NonNull @PathVariable(name = "id") Long id) {
        logger.info("Deleting order");
        orderServiceInt.deleteById(id);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Order> updateOrder(@NonNull @PathVariable(name = "id") Long id, @NonNull @Valid @RequestBody OrderRequestDto dto) {
        logger.info("Updating order");
        Order orderUpdated = orderRequestDtoConverter.toOrder(dto);
        EntityModel<Order> entityModel = modelAssembler.toModel(orderServiceInt.updateById(id, orderUpdated));
        return entityModel;
    }
}