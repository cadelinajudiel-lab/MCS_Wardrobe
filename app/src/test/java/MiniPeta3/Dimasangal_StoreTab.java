package com.example.myapplication;

import org.junit.Test;

public class Dimasangal_StoreTab {

    @Test
    public void main() {
        boolean isStoreOpen = true;
        String storeName = "MCS Wardrobe";

        if (isStoreOpen) {
            System.out.println("Welcome to " + storeName + "!");
            
            // Calling functions with store data
            displayStoreCategory("School Uniforms");
            
            displayProduct(101, "P.E. Shirt", 350.75, 50, "Medium");
            displayProduct(102, "School Blouse", 420.50, 20, "Small");
            displayProduct(103, "Necktie", 150.00, 0, "Standard");
            
        } else {
            System.out.println("The store tab is currently closed.");
        }
    }

    /**
     * Represents the category or item being viewed in the store.
     */
    public void displayStoreCategory(String category) {
        System.out.println("Viewing Category: " + category);
    }

    /**
     * Displays product details.
     * 
     * @param productId Unique identifier for each product displayed in the store tab.
     * @param productName Stores the name of the product (e.g., "P.E. Shirt").
     * @param productPrice Holds the price of the product, including decimal values.
     * @param stockQuantity Tracks how many units of the product are available.
     * @param variation Represents product variations (e.g., Small, Medium, Large).
     */
    public void displayProduct(int productId, String productName, double productPrice, int stockQuantity, String variation) {
        System.out.println("\n--- Product ID: " + productId + " ---");
        System.out.println("Product: " + productName);
        System.out.println("Price: P" + productPrice);
        System.out.println("Variation: " + variation);
        System.out.println("Stocks Available: " + stockQuantity);

        if (stockQuantity > 0) {
            System.out.println("Status: Available for Purchase");
        } else {
            System.out.println("Status: Out of Stock");
        }
    }
}
