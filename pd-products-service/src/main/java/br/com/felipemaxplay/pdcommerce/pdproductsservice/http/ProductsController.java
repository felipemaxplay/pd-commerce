package br.com.felipemaxplay.pdcommerce.pdproductsservice.http;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.http.data.request.ProductRequestDto;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.http.data.response.ProductModelAssembler;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.service.ProductService;
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
@RequestMapping(path = "/api/v1/products")
public class ProductsController implements ProductsControllerInt {
    private final Logger logger = LoggerFactory.getLogger(ProductsController.class);
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final ProductModelAssembler modelAssembler;
    private final ProductService productService;

    public ProductsController(ProductService productService, ProductModelAssembler modelAssembler, PagedResourcesAssembler pagedResourcesAssembler) {
        this.productService = productService;
        this.modelAssembler = modelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Product> createProduct(@NonNull @Valid @RequestBody ProductRequestDto dto) {
        logger.info("Creating new product");
        Product productPersist = new Product(dto.getName(), dto.getPrice(), dto.getDescription(), dto.getType(),
                dto.getBrand(), dto.getSku());
        EntityModel<Product> entityModel = modelAssembler.toModel(productService.save(productPersist));
        return entityModel;
    }

    @Override
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Product> getProduct(@NonNull @PathVariable(name = "id") Long id) {
        logger.info("Finding product by id");
        Product product = productService.getById(id);
        return modelAssembler.toModel(product);
    }

    @Override
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<EntityModel<Product>> getAllProduct(@NonNull @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        logger.info("Retrieving paged products");
        Page<Product> productPage = productService.findAll(pageable);
        PagedModel<EntityModel<Product>> pagedModel = pagedResourcesAssembler.toModel(productPage, modelAssembler);
        return pagedModel;
    }

    @Override
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@NonNull @PathVariable(name = "id") Long id) {
        logger.info("Deleting product");
        productService.deleteById(id);
    }

    @Override
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Product> updateProduct(@PathVariable(name = "id") Long id, @Valid @RequestBody ProductRequestDto dto) {
        logger.info("Updating product");
        Product productUpdate = new Product(id, dto.getName(), dto.getPrice(), dto.getDescription(),
                dto.getType(), dto.getBrand(), dto.getSku());
        EntityModel<Product> entityModel = modelAssembler.toModel(productService.updateById(productUpdate));
        return entityModel;
    }
}
