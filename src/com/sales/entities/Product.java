package com.sales;

import java.time.LocalDateTime;

public class Product {
    private String name;
    private String category;
    private double weight;
    private double price;
    private LocalDateTime creationDate;

    public Product(String name, String category, double weight, double price, LocalDateTime creationDate) {
        this.name = name;
        this.category = category;
        this.weight = weight;
        this.price = price;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
