package net.codejava.hibernate;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "Store")
@Table(name = "Stores")
public class Store {
    @Id
    @GeneratedValue
    @Column(name = "sid", nullable = false)
    private int sid;
    private String name;
    private int category;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Discount> discounts;

    public Store() {
        discounts = new LinkedList<Discount>();
    }

    public Store(String name, int category) {
        this.name = name;
        this.category = category;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
