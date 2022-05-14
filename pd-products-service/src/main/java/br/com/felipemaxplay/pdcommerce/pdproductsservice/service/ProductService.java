package br.com.felipemaxplay.pdcommerce.pdproductsservice.service;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductServiceInt {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        Product productPersist = productRepository.save(product);
        return productPersist;
    }
}
