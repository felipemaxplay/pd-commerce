package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class OrderRequestDto {
    @NotNull
    private Set<OrderProductRquestDto> products;

    @NotEmpty
    private String address;

    @NotEmpty
    @Email
    private String email;

    @Deprecated
    public OrderRequestDto() {
    }

    public Set<OrderProductRquestDto> getProducts() {
        return products;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}