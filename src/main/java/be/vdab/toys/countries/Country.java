package be.vdab.toys.countries;

import be.vdab.toys.customers.Customer;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Version
    private long version;
    @OneToMany(mappedBy = "country")
    @OrderBy("name")
    private Set<Customer> customers;

    protected Country() {
    }

    public Country(String name, Set<Customer> customers) {
        this.name = name;
        this.customers = customers;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getVersion() {
        return version;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }
}
