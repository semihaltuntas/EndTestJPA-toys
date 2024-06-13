package be.vdab.toys.orders;

import be.vdab.toys.customers.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate ordered;
    private LocalDate required;
    private LocalDate shipped;
    private String comments;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Version
    private long version;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    protected Order() {
    }

    public Order(LocalDate ordered, LocalDate required, LocalDate shipped, String comments, Status status, long version, Customer customer) {
        this.ordered = ordered;
        this.required = required;
        this.shipped = shipped;
        this.comments = comments;
        this.status = status;
        this.version = version;
        this.customer = customer;
    }
    public BigDecimal berekeningTotaalValues(){
        return orderDetails.stream()
                .map(orderDetail -> orderDetail.getValue())
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public long getId() {
        return id;
    }

    public LocalDate getOrdered() {
        return ordered;
    }

    public LocalDate getRequired() {
        return required;
    }

    public LocalDate getShipped() {
        return shipped;
    }

    public String getComments() {
        return comments;
    }

    public Status getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setShipped(LocalDate shipped) {
        this.shipped = shipped;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
