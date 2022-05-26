package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.repository;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
