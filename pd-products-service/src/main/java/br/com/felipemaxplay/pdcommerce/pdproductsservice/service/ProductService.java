package br.com.felipemaxplay.pdcommerce.pdproductsservice.service;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.event.ProductEvent;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ProductService implements ProductServiceInt {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ProductService(ProductRepository productRepository, ApplicationEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Product save(Product product) {
        Product productSaved = productRepository.save(product);
        eventPublisher.publishEvent(new ProductEvent(this, productSaved));
        return productSaved;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("product with id %d not found", id)));
    }

    @Override
    public void deleteById(Long id) {
        if(!productRepository.existsById(id)) {
            throw new NoResultException(String.format("product with id %d not found", id));
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product updateById(Product product) {
        if(!productRepository.existsById(product.getId())) {
            throw new NoResultException(String.format("product with id %d not found", product.getId()));
        }
        Product productUpdated = productRepository.save(product);
        eventPublisher.publishEvent(new ProductEvent(this, productUpdated));
        return productUpdated;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
