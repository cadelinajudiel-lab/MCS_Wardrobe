package com.example.myapplication;

import org.junit.Test;

public class Dimasangal_StoreTab {

    @Test
    public void main() {
        boolean isStoreOpen = true;

        // Stores store name
        String storeName = "MCS Wardrobe";

        // Stores category
        String category = "School Uniform Wardrobe";

        // Stores number of items
        int itemsCount = 10;

        if (isStoreOpen) {
            System.out.println("Store Tab is Active");
            System.out.println("Store Name: " + storeName);
            System.out.println("Category: " + category);
            System.out.println("Total Items: " + itemsCount);
        } else {
            System.out.println("Store Tab is currently unavailable.");
        }
    }
}
