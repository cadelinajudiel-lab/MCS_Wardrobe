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
        int uniformChoice;
        String uniformType;
        String shirt;
        String pants;
        // In a real application, you'd want to handle input differently in tests.
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("================================");
            System.out.println("      UNIFORM ORDER SYSTEM");
            System.out.println("================================");

            System.out.println("Choose Uniform Type:");
            System.out.println("1. Regular Uniform");
            System.out.println("2. P.E. Uniform");
            System.out.print("Choice: ");
            if (sc.hasNextInt()) {
                uniformChoice = sc.nextInt();
            } else {
                uniformChoice = 0;
            }
        } catch (Exception e) {
            uniformChoice = 0;
        }

        if (uniformChoice == 1) {
            uniformType = "Regular Uniform";
            shirt = "Regular Shirt";
            pants = "Regular Pants";
        } else if (uniformChoice == 2) {
            uniformType = "P.E. Uniform";
            shirt = "P.E. Shirt";
            pants = "P.E. Pants";
        } else {
            System.out.println("Invalid choice!");
            return;
        }
        
        System.out.println("Selected: " + uniformType);
    }
}
