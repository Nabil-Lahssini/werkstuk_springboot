package com.example.werkstuk.product;

import java.util.ArrayList;

public class ShoppingCart {
    public ArrayList<Product> products;
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

    public ShoppingCart() {
        products = new ArrayList<Product>();
        total = 0;
    }
}
