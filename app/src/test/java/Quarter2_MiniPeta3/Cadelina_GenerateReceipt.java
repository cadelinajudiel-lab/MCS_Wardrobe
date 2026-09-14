package Quarter2_MiniPeta3;

import java.util.Scanner;

public class Cadelina_GenerateReceipt {

    public static void main(String[] args) {
        // Use try-with-resources to ensure the Scanner is closed automatically
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("--- Generate Receipt ---");
            
            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine();
            
            System.out.print("Enter Item Name: ");
            String itemName = scanner.nextLine();
            
            System.out.print("Enter Item Price: ");
            // Using Double.parseDouble(scanner.nextLine()) is safer than scanner.nextDouble()
            // because it consumes the newline character, preventing skips in subsequent reads.
            double price = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            double total = price * quantity;
            
            // Print Receipt
            System.out.println("\n----------------------");
            System.out.println("Receipt for: " + customerName);
            System.out.println("Item: " + itemName);
            System.out.println("Price: " + price);
            System.out.println("Quantity: " + quantity);
            System.out.println("Total Amount: " + total);
            System.out.println("----------------------");
        } catch (NumberFormatException e) {
            System.err.println("Invalid input: Please enter numeric values for price and quantity.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
