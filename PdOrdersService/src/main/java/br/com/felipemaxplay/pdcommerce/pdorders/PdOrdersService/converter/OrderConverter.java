package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.converter;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.response.OrderEmailDto;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.response.OrderProductEmailDto;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderConverter {
    public static OrderEmailDto toOrderEmailDto(Order order) {
        Set<OrderProductEmailDto> products = order.getProducts().stream()
                .map(pd -> new OrderProductEmailDto(
                        pd.getProduct().getName(),
                        pd.getQtd(),
                        pd.getPrice(),
                        pd.getTotalPrice()))
                .collect(Collectors.toSet());
        OrderEmailDto emailDto = new OrderEmailDto(order.getId(), order.getEmail(), order.getAddress(), order.getPrice(), products);
        return emailDto;
    }
}