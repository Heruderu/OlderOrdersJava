package com.sales;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Customer customer;
    private double grandTotal;
    private LocalDateTime datePlaced;
    private List<Item> items;

    public Order(Customer customer, LocalDateTime datePlaced, List<Item> items) {
        this.grandTotal = 0;
        for (Item item : items) {
            if (item.getProduct().getCreationDate().isAfter(datePlaced))
                throw new DateTimeException("Product creation should not be after order date");
            this.grandTotal += item.getCost() + item.getTaxAmount() + item.getShippingFee();
        }
        this.customer = customer;
        this.datePlaced = datePlaced;
        this.items = items;
    }

    public Order(Customer customer, double grandTotal, LocalDateTime datePlaced, List<Item> items) {
        this.customer = customer;
        this.grandTotal = grandTotal;
        this.datePlaced = datePlaced;
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public LocalDateTime getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(LocalDateTime datePlaced) {
        this.datePlaced = datePlaced;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
