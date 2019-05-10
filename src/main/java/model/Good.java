package model;

public class Good {
    private String id;
    private String owner;
    private String name;
    private double price;
    private String description;

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
}
