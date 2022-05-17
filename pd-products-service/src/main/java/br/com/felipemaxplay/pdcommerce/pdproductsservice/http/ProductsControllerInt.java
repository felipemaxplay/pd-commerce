package br.com.felipemaxplay.pdcommerce.pdproductsservice.http;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.http.data.request.ProductRequestDto;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ProductsControllerInt {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<Product> createProduct(@NonNull @Valid @RequestBody ProductRequestDto dto);

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<Product> getProduct(@NonNull @PathVariable(name = "id") Long id);

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    PagedModel<EntityModel<Product>> getAllProduct(@NonNull @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable);

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(@NonNull @PathVariable(name = "id") Long id);

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<Product> updateProduct(@PathVariable(name = "id") Long id, @Valid @RequestBody ProductRequestDto dto);
}
