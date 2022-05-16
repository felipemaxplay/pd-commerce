package br.com.felipemaxplay.pdcommerce.pdproductsservice.http;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.http.data.request.ProductRequestDto;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductsController implements ProductsControllerInt {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody ProductRequestDto dto) {
        Product productPersist = new Product(dto.getName(), dto.getPrice(), dto.getDescription(), dto.getType(),
                dto.getBrand(), dto.getSku());
        return productService.save(productPersist);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable(name = "id") Long id) {
        return productService.getById(id);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable(name = "id") Long id, @Valid @RequestBody ProductRequestDto dto) {
        Product productUpdate = new Product(id, dto.getName(), dto.getPrice(), dto.getDescription(),
                dto.getType(), dto.getBrand(), dto.getSku());
        return productService.updateById(productUpdate);
    }
}
