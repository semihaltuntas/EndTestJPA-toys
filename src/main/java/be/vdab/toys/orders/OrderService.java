package be.vdab.toys.orders;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    Optional<Order> findByIdWithDetails(long id){
        return orderRepository.findById(id);
    }




}
