package br.com.felipemaxplay.pdcommerce.pdproductsservice.event;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import org.springframework.context.ApplicationEvent;

public class ProductEvent extends ApplicationEvent {

    private final Product product;

    public ProductEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}