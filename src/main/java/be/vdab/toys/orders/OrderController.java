package be.vdab.toys.orders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    private record OrderBeknoptUnshipped(long id, LocalDate ordered, LocalDate required,
                                                String customerName, Status status) {
        OrderBeknoptUnshipped(Order order) {
            this(order.getId(), order.getOrdered(), order.getRequired(),
                    order.getCustomer().getName(), order.getStatus());
        }
    }

    @GetMapping("unshipped")
    Stream<OrderBeknoptUnshipped> findOrdersUnshipped() {
        return orderService.findOrdersWithoutShippedAndCanceled()
                .stream()
                .map(order -> new OrderBeknoptUnshipped(order));
    }
}
