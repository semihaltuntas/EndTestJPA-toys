package be.vdab.toys.customers;

import be.vdab.toys.countries.Country;
import be.vdab.toys.orders.Order;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String streetAndNumber;
    private String city;
    private String state;
    private String postalCode;
    @Version
    private long version;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;

    @OneToMany(mappedBy = "customer")
    @OrderBy("ordered")
    private Set<Order> orders;

    protected Customer() {
    }

    public Customer(String name, String streetAndNumber, String city, String state, String postalCode,  Country country) {
        this.name = name;
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public long getVersion() {
        return version;
    }

    public Country getCountry() {
        return country;
    }
}
