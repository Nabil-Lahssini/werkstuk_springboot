package com.example.werkstuk.product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class ShoppingCart {
    @Id
    public String id;
    @ManyToMany
    public List<Product> products;


    public float total;
    public void add(Product product){
        products.add(product);
    }

    public float getTotal() {
        int temp = 0;
        for (Product product: products){
            temp += product.getPrice();
        }
        return temp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ShoppingCart() {
        products = new ArrayList<Product>();
        total = 0;
    }
}
