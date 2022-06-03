package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.request.OrderRequestDto;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface OrderControllerInt {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<Order> createOrder(@Valid @RequestBody OrderRequestDto dto);

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<Order> findOrder(@NonNull @PathVariable(name = "id") Long id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    PagedModel<EntityModel<Order>> findAllPaged(@NonNull @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@NonNull @PathVariable(name = "id") Long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<Order> updateOrder(@NonNull @PathVariable(name = "id") Long id, @NonNull @Valid @RequestBody OrderRequestDto dto);
}
