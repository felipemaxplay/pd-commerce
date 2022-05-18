package br.com.felipemaxplay.pdcommerce.pdproductsservice.http.data.response;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.http.ProductsController;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    @Override
    public EntityModel<Product> toModel(Product entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ProductsController.class).getProduct(entity.getId())).withSelfRel(),
                linkTo(methodOn(ProductsController.class).getAllProduct(null)).withRel("products"));
    }
}
