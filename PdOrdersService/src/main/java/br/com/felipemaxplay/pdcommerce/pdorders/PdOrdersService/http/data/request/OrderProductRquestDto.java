package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OrderProductRquestDto {
    @NotNull
    private Long product;

    @NotNull
    private BigDecimal qtd;

    @Deprecated
    public OrderProductRquestDto() {
    }

    public Long getProduct() {
        return product;
    }

    public BigDecimal getQtd() {
        return qtd;
    }
}
