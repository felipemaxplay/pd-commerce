package br.com.felipemaxplay.pdcommerce.pdEmailService.http.data.response;

import java.math.BigDecimal;

public class OrderProductDto {
    private String product;
    private BigDecimal qtd;
    private BigDecimal price;
    private BigDecimal totalPrice;

    @Deprecated
    public OrderProductDto() {

    }

    public OrderProductDto(String product, BigDecimal qtd, BigDecimal price, BigDecimal totalPrice) {
        this.product = product;
        this.qtd = qtd;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public String getProduct() {
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
}
