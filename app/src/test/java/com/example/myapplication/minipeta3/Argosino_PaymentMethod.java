package com.example.myapplication.minipeta3;

import java.util.Scanner;

public class Argosino_PaymentMethod {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double amount;
        int choice;
        String paymentMethod = "";

        System.out.println("===== PAYMENT SYSTEM =====");

        System.out.print("Enter Amount to Pay: ");
        amount = sc.nextDouble();

        System.out.println("\nChoose Payment Method:");
        System.out.println("1. Cash");
        System.out.println("2. GCash");
        System.out.println("3. Credit Card");
        System.out.print("Enter Choice (1-3): ");
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                paymentMethod = "Cash";
                break;
            case 2:
                paymentMethod = "GCash";
                break;
            case 3:
                paymentMethod = "Credit Card";
                break;
            default:
                paymentMethod = "Invalid Payment Method";
        }

        System.out.println("\n===== PAYMENT RECEIPT =====");
        System.out.println("Amount         : " + amount);
        System.out.println("Payment Method : " + paymentMethod);

        if (choice >= 1 && choice <= 3) {
            System.out.println("Payment Successful!");
        } else {
            System.out.println("Payment Failed!");
        }

        sc.close();
    }
}
