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

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void deleteById(Long id) {
        if(!productRepository.existsById(id)) {
            throw new RuntimeException();
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product updateById(Product product) {
        if(!productRepository.existsById(product.getId())) {
            throw new RuntimeException();
        }
        return productRepository.save(product);
    }
}
