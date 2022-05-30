package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "pd_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "price", nullable = false, scale = 18, precision = 2)
    private BigDecimal price;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "type", nullable = false, length = 60)
    private String type;

    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "sku", nullable = false, length = 15, unique = true)
    private String sku;

    @Deprecated
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
