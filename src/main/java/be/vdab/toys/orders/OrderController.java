package be.vdab.toys.orders;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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

    private record OrderBeknoptWithCustomerNameAndCountryNameAndTotaalOfValues(long id, LocalDate ordered, LocalDate required, String customerName, String countryName,
                                                                               BigDecimal totaalValue, List<OrderDetailWithValueEnProductName> details) {
        OrderBeknoptWithCustomerNameAndCountryNameAndTotaalOfValues(Order order) {
            this(order.getId(), order.getOrdered(), order.getRequired(),
                    order.getCustomer().getName(), order.getCustomer().getCountry().getName(),
                    order.berekeningTotaalValues(),
                    order.getOrderDetails()
                            .stream()
                            .map(OrderDetailWithValueEnProductName::new)
                            .collect(Collectors.toList()));
        }
    }

    private record OrderDetailWithValueEnProductName(int ordered, BigDecimal priceEach, BigDecimal value, String productName) {
        OrderDetailWithValueEnProductName(OrderDetail orderDetail) {
            this(orderDetail.getOrdered(), orderDetail.getPriceEach(),
                    orderDetail.getValue(), orderDetail.getProduct().getName());
        }
    }

    @GetMapping("{id}")
    OrderBeknoptWithCustomerNameAndCountryNameAndTotaalOfValues findDetailsById(@PathVariable long id) {
        return orderService.findByIdWithDetails(id)
                .map(OrderBeknoptWithCustomerNameAndCountryNameAndTotaalOfValues::new)
                .orElseThrow(OrderNietGevondenException::new);
    }

    @PostMapping("{id}/shippings")
    void updateByOrderIdDeStockAndInOrderAndMarkOrderAsShippedWithSystemDate(@PathVariable long id){
        orderService.updateByOrderId(id);
    }
}
