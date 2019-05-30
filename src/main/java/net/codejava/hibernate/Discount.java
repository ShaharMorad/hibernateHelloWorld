package net.codejava.hibernate;

import javax.persistence.*;

@Entity(name = "Discount")
@Table (name = "Discounts")
public class Discount {

    @Id
    private int id;

    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
    private String discountJson;


    public Discount(int id, Store store, String json) {
        this.id = id;
        this.store = store;
        this.discountJson = json;
    }

    public Discount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getDiscountJson() {
        return discountJson;
    }

    public void setDiscountJson(String discountJson) {
        this.discountJson = discountJson;
    }
}
