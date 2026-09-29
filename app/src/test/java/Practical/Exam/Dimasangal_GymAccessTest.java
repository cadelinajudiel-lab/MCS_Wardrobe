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
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Member Name: ");
                    String memberName = scanner.nextLine();

                    System.out.println("Member Registered: " + memberName);
                    break;

                case 2:
                    System.out.print("Enter Member Name: ");
                    String checkName = scanner.nextLine();

                    System.out.println("Access Granted for " + checkName);
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