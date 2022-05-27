package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "pd_order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "qtd", nullable = false)
    private BigDecimal qtd;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Deprecated
    public OrderProduct() {
    }

    public OrderProduct(@NonNull Product product, @NonNull BigDecimal qtd, @NonNull BigDecimal price) {
        this.product = Objects.requireNonNull(product);
        this.qtd = Objects.requireNonNull(qtd);
        this.price = product.getPrice();
        this.totalPrice = qtd.multiply(price);
    }

    public void setOrder(Order order) {
        this.order = Objects.requireNonNull(order);
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getQtd() {
        return qtd;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct orderProduct = (OrderProduct) o;
        return Objects.equals(order, orderProduct.order) && Objects.equals(product, orderProduct.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
