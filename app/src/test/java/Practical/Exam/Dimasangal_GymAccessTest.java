package Practical.Exam;

import androidx.annotation.NonNull;

import org.junit.Test;

public class Dimasangal_GymAccessTest {

    @Test
    public void TestGymMenu() {
    }
    public void start(@NonNull Scanner scanner) {

            int choice;

            do {
                System.out.println("\n=== GYM ACCESS MENU ===");
                System.out.println("1. Register Member");
                System.out.println("2. Check Access");
                System.out.println("3. Exit");
                System.out.print("Enter Choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Clears leftover Enter key

                switch (choice) {

                    case 1:
                        System.out.print("Enter Member Name: ");
                        String memberName = scanner.nextLine();

                        System.out.print("Enter Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine(); // Clears leftover Enter key

                        System.out.println("Member Registered!");
                        System.out.println("Name: " + memberName);
                        System.out.println("Age: " + age);
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
        }
}
