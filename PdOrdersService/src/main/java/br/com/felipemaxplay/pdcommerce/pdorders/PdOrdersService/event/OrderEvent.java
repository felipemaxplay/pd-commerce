package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.event;

import br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService.model.Order;
import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {

    private final Order order;

    public OrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
