package org.example.product;

import java.time.LocalDate;

public class Product {
    private final String category;
    private double price;
    private boolean isDiscount;
    private final LocalDate dateTime;

    public Product(String category, double price, boolean isDiscount, LocalDate dateTime) {
        this.category = category;
        this.price = price;
        this.isDiscount = isDiscount;
        this.dateTime = dateTime;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "{" +
                "category:'" + category + '\'' +
                ", price:" + price +
                ", isDiscount:" + isDiscount +
                ", dateTime:" + dateTime +
                '}';
    }
}
