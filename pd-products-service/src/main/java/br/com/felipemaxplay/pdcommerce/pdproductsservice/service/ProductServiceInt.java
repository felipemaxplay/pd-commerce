package br.com.felipemaxplay.pdcommerce.pdproductsservice.service;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServiceInt {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    Product updateById(Product product);

    Page<Product> findAll(Pageable pageable);
}
