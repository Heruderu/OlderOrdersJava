package com.sales.entities;

public class Item {
    private double cost;
    private double shippingFee;
    private double taxAmount;
    private Product product;

    public Item(Product product) {
        this.product = product;
        this.cost = product.getPrice() * 0.9;
        this.taxAmount = product.getPrice() * 0.1;
        this.shippingFee = 3.99;
    }

    public Item(double cost, double shippingFee, double taxAmount, Product product) {
        this.cost = cost;
        this.shippingFee = shippingFee;
        this.taxAmount = taxAmount;
        this.product = product;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
