package com.sales;

public class Customer {
    private String name;
    private String email;
    private String phone;
    private String shippingAddress;

    public Customer(String name, String email, String phone, String shippingAddress) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.shippingAddress = shippingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
