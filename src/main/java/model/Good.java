package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Entity
@Table(name = "goods")
public class Good {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "goods")
    private Set<Order> orders = new HashSet<>();

    public Good(){}

    public Good(String id, String owner, String name, String description, double price) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.price = price;
        this.description = description;
    }


    public String getOwner() {
        return owner;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
