package practical.exam;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Dimasangal_GymAccessTest {

    @Test
    public void TestGymMenu() {

        StringBuilder automatedInput = new StringBuilder();

        System.out.println("=== GENERATING GYM ACCESS TEST DATA ===");

        // Register Member
        automatedInput.append("1\n");
        automatedInput.append("Denise\n");
        automatedInput.append("2\n");

        // Check Access
        automatedInput.append("2\n");
        automatedInput.append("15\n");

        // Exit
        automatedInput.append("3\n");

        System.out.println("=== TEST DATA GENERATION COMPLETE ===");

        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(automatedInput.toString().getBytes());

        Scanner scanner = new Scanner(inputStream);

        int choice;

        do {

            System.out.println("\n=== GYM ACCESS MENU ===");
            System.out.println("1. Register Member");
            System.out.println("2. Check Access");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    String memberName = scanner.nextLine();
                    int membershipType = scanner.nextInt();
                    scanner.nextLine();

                    double membershipFee;

                    if (membershipType == 1) {
                        membershipFee = 500.00;
                    } else {
                        membershipFee = 1000.00;
                    }

                    System.out.println("Member Registered: " + memberName);
                    System.out.println("Membership Fee: PHP " + membershipFee);
                    break;

                case 2:

                    int visits = scanner.nextInt();
                    scanner.nextLine();

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
