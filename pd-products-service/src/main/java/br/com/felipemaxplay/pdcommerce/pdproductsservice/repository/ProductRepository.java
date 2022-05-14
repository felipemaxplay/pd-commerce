package br.com.felipemaxplay.pdcommerce.pdproductsservice.repository;

import br.com.felipemaxplay.pdcommerce.pdproductsservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
