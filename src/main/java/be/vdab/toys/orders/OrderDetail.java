package be.vdab.toys.orders;

import be.vdab.toys.products.Product;
import jakarta.persistence.*;
@Entity
@Table(name = "orderdetails")
public class OrderDetail {
    @EmbeddedId
    OrderDetailKey id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    Product product;


}
