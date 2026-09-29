package Practical.Exam;

import java.util.Scanner;

public class Dimasangal_GymAccessTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n=== GYM ACCESS MENU ===");
            System.out.println("1. Register Member");
            System.out.println("2. Check Access");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {

                case 1:

                    System.out.print("Enter Member Name: ");
                    String memberName = scanner.nextLine();

                    System.out.print("Enter Membership Type (1-Regular, 2-Premium): ");
                    int membershipType = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer

                    double membershipFee;

                    if (membershipType == 1) {
                        membershipFee = 500.00;
                    } else {
                        membershipFee = 1000.00;
                    }

                    System.out.println("\n=== MEMBER DETAILS ===");
                    System.out.println("Name: " + memberName);
                    System.out.println("Membership Fee: PHP " + membershipFee);

                    break;

                case 2:

                    System.out.print("Enter Number of Visits This Month: ");
                    int visits = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer

                    System.out.println("\n=== ACCESS STATUS ===");

                    if (visits >= 12) {
                        System.out.println("VIP Access Granted");
                    } else {
                        System.out.println("Regular Access Granted");
                    }

                    break;

                case 3:

                    System.out.println("Thank you for using Gym Access System!");
                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 3);

        scanner.close();
    }
}
