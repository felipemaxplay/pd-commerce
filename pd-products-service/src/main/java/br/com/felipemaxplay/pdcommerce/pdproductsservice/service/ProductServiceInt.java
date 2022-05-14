package br.com.felipemaxplay.pdcommerce.pdproductsservice.service;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;

public interface ProductServiceInt {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    Product updateById(Product product);
}
