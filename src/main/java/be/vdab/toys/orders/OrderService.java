package be.vdab.toys.orders;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    List<Order> findOrdersWithoutShippedAndCanceled() {

        return orderRepository.findWithoutShippedAndCanceled();
    }

    Optional<Order> findByIdWithDetails(long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    void updateByOrderId(long orderId) {
        Order order = orderRepository.findAndLockById(orderId).orElseThrow(OrderNietGevondenException::new);
        if(order.getStatus() == Status.SHIPPED){
            throw new DezeOrderHeeftAlDeStatusShippedException();
        }

        order.getOrderDetails().forEach(orderDetail -> {
            orderDetail.getProduct().setInOrder(orderDetail.getOrdered());
            orderDetail.getProduct().setInStock(orderDetail.getOrdered());
        });

        order.setShipped(LocalDate.now());
        order.setStatus(Status.SHIPPED);
        orderRepository.save(order);
    }
}





