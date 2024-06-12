package be.vdab.toys.productlines;

import be.vdab.toys.products.Product;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "productlines")
public class Productline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @Version
    private long version;
    @OneToMany(mappedBy = "productline")
    @OrderBy("name")
    private Set<Product> products;

    protected Productline() {
    }

    public Productline(String name, String description, Set<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }
}
