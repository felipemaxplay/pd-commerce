package br.com.felipemaxplay.pdcommerce.pdproductsservice.http;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.service.ProductServiceInt;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductsController implements ProductsControllerInt {

    private final ProductServiceInt productServiceInt;

    public ProductsController(ProductServiceInt productServiceInt) {
        this.productServiceInt = productServiceInt;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        Product productPersist = productServiceInt.save(product);
        return productPersist;
    }

}
