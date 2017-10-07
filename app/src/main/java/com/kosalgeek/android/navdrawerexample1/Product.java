package com.kosalgeek.android.navdrawerexample1;


public class Product {
    private String name;
    private int qty;
    private double price;

    public Product() {
    }

    public Product(String name, int qty, double price) {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
