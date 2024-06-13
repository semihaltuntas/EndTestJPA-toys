package be.vdab.toys.orders;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.status!='SHIPPED' and o.status != 'CANCELED' order by o.id")
    @EntityGraph(attributePaths = "customer")
    List<Order> findWithoutShippedAndCanceled();


    @Query("select o from Order o join fetch o.orderDetails od where o.id = :id order by od.product.name")
    Optional<Order> findById(long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select o from Order o where o.id = :id")
    Optional<Order> findAndLockById(long id);
}
