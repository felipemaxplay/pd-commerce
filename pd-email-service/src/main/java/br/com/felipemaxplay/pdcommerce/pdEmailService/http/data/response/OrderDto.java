package br.com.felipemaxplay.pdcommerce.pdEmailService.http.data.response;

import java.math.BigDecimal;
import java.util.Set;

public class OrderDto {
    private Long id;
    private String email;
    private String address;
    private BigDecimal totalPrice;
    private Set<OrderProductDto> products;

    @Deprecated
    public OrderDto() {
    }

    public OrderDto(Long id, String email, String address, BigDecimal totalPrice, Set<OrderProductDto> products) {
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

    public Set<OrderProductDto> getProducts() {
        return products;
    }
}
