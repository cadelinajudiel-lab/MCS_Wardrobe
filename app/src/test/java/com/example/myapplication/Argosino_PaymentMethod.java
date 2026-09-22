package com.example.myapplication;

import java.util.Scanner;

class Argosino_PaymentMeht {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            double amount;
            int choice;
            String paymentMethod = "";

            System.out.println("Enter the amount to be paid: ");
            amount = sc.nextDouble();

            System.out.println("Select the payment method: ");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit Card");
            System.out.println("3. Gcash");
            System.out.println("4. Cash ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    paymentMethod = "Credit Card";
                    break;
                case 2:
                    paymentMethod = "Debit Card";
                    break;
                case 3:
                    paymentMethod = "Gcash";
                    break;
                case 4:
                    paymentMethod = "Cash";
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            System.out.println("\n===== PAYMENT RECEIPT =====");
            System.out.println("Payment Method Selected: " + paymentMethod);
            System.out.println("Amount to be paid: $" + amount);

            if (choice >= 1 && choice <= 3) {
                System.out.println("Processing payment through " + paymentMethod + "...");
                System.out.println("Payment successful!");
            } else if (choice == 4) {
                System.out.println("Please pay the amount in cash at the counter.");
            }
        }
    }
}