package be.vdab.toys.orders;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from Order o where o.status!='SHIPPED' and o.status != 'CANCELED' order by o.id")
    @EntityGraph(attributePaths = "customer")
    List<Order> findWithoutShippedAndCanceled();

}
