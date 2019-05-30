package net.codejava.hibernate;

import javax.persistence.*;

@Entity(name = "Product")
@Table(name = "Products")
public class Product {
    private int pid;
    private String name;
    private int category;

    public Product() {
    }

    public Product(String name, int category) {
        this.name = name;
        this.category = category;
    }

    @Id
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
}
