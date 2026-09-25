package minipeta3;

import org.junit.Test;
import java.util.Scanner;

public class Cadelina_PlaceOrder {

    @Test
    public void testPlaceOrder() {
        // Run main method logic
        main(new String[0]);
    }

    public static void main(String[] args) {
        // Define prices
        int regularPrice = 500;
        int pePrice = 450;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("================================");
            System.out.println("        PLACE NEW ORDER");
            System.out.println("================================");
            System.out.println("Choose your Uniform:");
            System.out.println("1. Regular Uniform - PHP " + regularPrice + ".00");
            System.out.println("2. P.E. Uniform     - PHP " + pePrice + ".00");
            System.out.print("Enter Choice (1 or 2): ");

            int choice = 0;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input numeric choice required.");
                return;
            }

            String uniformType = "";
            int price = 0;

            if (choice == 1) {
                uniformType = "Regular Uniform";
                price = regularPrice;
            } else if (choice == 2) {
                uniformType = "P.E. Uniform";
                price = pePrice;
            } else {
                System.out.println("Invalid choice!");
                return;
            }

            System.out.println("Selected: " + uniformType);
            System.out.print("Enter Quantity: ");
            
            int quantity = 0;
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
            }
            
            if (quantity <= 0) {
                System.out.println("Invalid quantity!");
                return;
            }

            int totalAmount = price * quantity;
            System.out.println("Total Amount to Pay: PHP " + totalAmount + ".00");

            System.out.print("Enter Cash Amount: ");
            double cashAmount = 0;
            if (scanner.hasNextDouble()) {
                cashAmount = scanner.nextDouble();
            }

            if (cashAmount < totalAmount) {
                System.out.println("Insufficient cash payment failed!");
                return;
            }

            double change = cashAmount - totalAmount;

            // Connect and pass the data to Cadelina_GenerateReceiptTest to print the final receipt
            Cadelina_GenerateReceiptTest.printReceipt(uniformType, price, quantity, totalAmount, cashAmount, change);

        } catch (Exception e) {
            System.out.println("An error occurred or input ended: " + e.getMessage());
        }
    }
}
