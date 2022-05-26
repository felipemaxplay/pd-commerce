package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pd_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private LocalDateTime date;

    @Column
    private BigDecimal price;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderProduct> products;

    @Deprecated
    public Order() {
    }

    public Order(@NonNull String address, @NonNull String email, @NonNull Set<OrderProduct> products) {
        this.finalizeOrder(address, email, products);
        this.date = LocalDateTime.now();
    }

    private void finalizeOrder(@NonNull String address, @NonNull String email, @NonNull Set<OrderProduct> products) {
        this.address = Objects.requireNonNull(address);
        this.email = Objects.requireNonNull(email);
        Objects.requireNonNull(products);
        products.forEach(pd -> pd.setOrder(this));
        if (!this.products.isEmpty()) {
            this.products.clear();
        }
        this.products.addAll(products);
        this.price = products.stream()
                .map(OrderProduct::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<OrderProduct> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
