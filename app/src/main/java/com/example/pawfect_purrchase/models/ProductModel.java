package com.example.pawfect_purrchase.models;

public class ProductModel {
    private int image;
    private String name;
    private String price;
    private String description;
    private double rating;

    private int quantity;
    private double totalPrice;
    public ProductModel(int image, String name, String price, String description, double rating) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }
}
