package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.converter;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.http.data.request.OrderRequestDto;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.OrderProduct;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Product;
import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderRequestDtoConverter {
    private final ProductRepository productRepository;

    public OrderRequestDtoConverter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Order toOrder(OrderRequestDto dto) {
        Set<OrderProduct> products = toOrderProducts(dto);
        return new Order(dto.getAddress(), dto.getEmail(), products);
    }

    private Set<OrderProduct> toOrderProducts(OrderRequestDto dto) {
        return dto.getProducts().stream()
                .map(pd -> {
                    Product product = productRepository.findById(pd.getProduct())
                            .orElseThrow(() -> new RuntimeException(String.format("Not Found %d", pd.getProduct())));
                    return new OrderProduct(product, pd.getQtd(), product.getPrice());
                })
                .collect(Collectors.toSet());
    }
}