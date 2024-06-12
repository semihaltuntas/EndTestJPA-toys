package be.vdab.toys.orders;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    List<Order> findOrdersWithoutShippedAndCanceled(){
        return orderRepository.findWithoutShippedAndCanceled();
    }
}
