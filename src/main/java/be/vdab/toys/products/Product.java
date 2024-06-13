package be.vdab.toys.products;

import be.vdab.toys.orders.OrderDetail;
import be.vdab.toys.productlines.Productline;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String scale;
    private String description;
    private int inStock;
    private int inOrder;
    private BigDecimal price;

    @Version
    private long version;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "productlineId")
    private Productline productline;
    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails;
    protected Product() {
    }

    public Product(String name, String scale, String description, int inStock, int inOrder, BigDecimal price, Productline productline, Set<OrderDetail> orderDetails) {
        this.name = name;
        this.scale = scale;
        this.description = description;
        this.inStock = inStock;
        this.inOrder = inOrder;
        this.price = price;
        this.productline = productline;
        this.orderDetails = orderDetails;
    }

    public String getName() {
        return name;
    }

    public String getScale() {
        return scale;
    }

    public String getDescription() {
        return description;
    }

    public int getInStock() {
        return inStock;
    }

    public int getInOrder() {
        return inOrder;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getVersion() {
        return version;
    }

    public Productline getProductline() {
        return productline;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
}
