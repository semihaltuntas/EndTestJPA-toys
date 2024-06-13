package be.vdab.toys.orders;

import be.vdab.toys.products.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

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

    private int ordered;
    private BigDecimal priceEach;
    protected OrderDetail() {
    }

    public OrderDetail(Order order, Product product, int ordered, BigDecimal priceEach) {
        this.order = order;
        this.product = product;
        this.ordered = ordered;
        this.priceEach = priceEach;
    }

    public BigDecimal getValue(){
        return priceEach.multiply(BigDecimal.valueOf(ordered));
    }

    public int getOrdered() {
        return ordered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public Product getProduct() {
        return product;
    }
}
