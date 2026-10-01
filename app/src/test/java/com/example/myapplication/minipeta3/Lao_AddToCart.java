package com.example.myapplication.minipeta3;

import androidx.annotation.NonNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Lao_AddToCart {

    @Test
    public void main() {
        ShoppingCart cart = new ShoppingCart();
        Product shirt = new Product("School Shirt", 250.00);
        Product pants = new Product("School Pants", 350.00);

        cart.addToCart(shirt);
        cart.addToCart(pants);
        cart.showCart();

        cart.removeFromCart(shirt);
        cart.showCart();
    }
}

class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " (₱" + price + ")";
    }
}

class ShoppingCart {
    private final List<Product> items = new ArrayList<>();

    public void addToCart(Product product) {
        items.add(product);
        System.out.println(product + " added to cart.");
    }

    public void removeFromCart(Product product) {
        if (items.remove(product)) {
            System.out.println(product + " removed from cart.");
        } else {
            System.out.println(product + " was not found in the cart.");
        }
    }

    public double getTotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("Cart contents:");
        for (Product item : items) {
            System.out.println(" - " + item);
        }
        System.out.printf("Total: ₱%.2f%n", getTotal());
    }
}
