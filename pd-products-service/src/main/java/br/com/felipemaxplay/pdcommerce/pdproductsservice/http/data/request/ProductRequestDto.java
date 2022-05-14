package br.com.felipemaxplay.pdcommerce.pdproductsservice.http.data.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductRequestDto {
    @NotEmpty
    private String name;
    @NotNull
    private BigDecimal price;
    @NotEmpty
    private String description;
    @NotEmpty
    private String type;
    @NotEmpty
    private String brand;
    @NotEmpty
    private String sku;

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
}
