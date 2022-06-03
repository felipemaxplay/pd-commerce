package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.response;

import java.math.BigDecimal;
import java.util.Set;

public class OrderEmailDto {
    private Long id;
    private String email;
    private String address;
    private BigDecimal totalPrice;
    private Set<OrderProductEmailDto> products;

    @Deprecated
    public OrderEmailDto() {
    }

    public OrderEmailDto(Long id, String email, String address, BigDecimal totalPrice, Set<OrderProductEmailDto> products) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Set<OrderProductEmailDto> getProducts() {
        return products;
    }
}