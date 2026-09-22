package com.example.myapplication;

import org.junit.Test;
import java.util.Scanner;

public class Cadelina_GenerateReceipt {
    @Test
    public void testGenerateReceipt() {
        // Run main method logic
        main(new String[0]);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("================================");
            System.out.println("      UNIFORM RECEIPT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Regular Uniform - PHP 500.00");
            System.out.println("2. P.E. Uniform     - PHP 450.00");
            System.out.print("Choice: ");
            
            int choice = sc.hasNextInt() ? sc.nextInt() : 0;
            String type = (choice == 1) ? "Regular Uniform" : (choice == 2) ? "P.E. Uniform" : "Invalid";
            int price = (choice == 1) ? 500 : (choice == 2) ? 450 : 0;

            if (price > 0) {
                System.out.print("Quantity: ");
                int qty = sc.nextInt();
                int total = price * qty;
                System.out.println("Total: PHP " + total);
                
                System.out.print("Cash: ");
                double cash = sc.nextDouble();
                if (cash >= total) {
                    printReceipt(type, price, qty, total, cash, cash - total);
                } else {
                    System.out.println("Insufficient funds!");
                }
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public static void printReceipt(String type, int price, int qty, int total, double cash, double change) {
        System.out.println("\n================================");
        System.out.println("        OFFICIAL RECEIPT");
        System.out.println("================================");
        System.out.println("Item:      " + type);
        System.out.println("Price:     PHP " + price + ".00");
        System.out.println("Quantity:  " + qty);
        System.out.println("--------------------------------");
        System.out.println("TOTAL:     PHP " + total + ".00");
        System.out.println("CASH:      PHP " + cash);
        System.out.println("CHANGE:    PHP " + change);
        System.out.println("================================");
        System.out.println("   Thank you for your order!");
        System.out.println("================================");
    }
}
