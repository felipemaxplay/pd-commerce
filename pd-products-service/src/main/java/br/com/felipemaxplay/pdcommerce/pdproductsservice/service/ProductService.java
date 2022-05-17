package br.com.felipemaxplay.pdcommerce.pdproductsservice.service;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import br.com.felipemaxplay.pdcommerce.pdproductsservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ProductService implements ProductServiceInt {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
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
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
