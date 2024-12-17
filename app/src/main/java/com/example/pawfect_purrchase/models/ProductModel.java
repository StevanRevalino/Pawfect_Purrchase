package com.example.pawfect_purrchase.models;

public class ProductModel {
    private int image;
    private String name;
    private String price;
    private String description;

    private int quantity;
    private double totalPrice;
    public ProductModel(int image, String name, String price, String description) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}