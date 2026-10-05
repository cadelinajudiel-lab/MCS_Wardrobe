package practical.exam;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

class CinemaTicketSystem {
    public void start(Scanner scanner) {
        int choice;
        do {
            System.out.println("--- CINEMA TICKET SYSTEM ---");
            System.out.println("1. Buy Ticket");
            System.out.println("2. Buy Snacks");
            System.out.println("3. Exit");
            if (!scanner.hasNextInt()) break;
            choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("Enter age: ");
                if (scanner.hasNextInt()) {
                    int age = scanner.nextInt();
                    if (age < 18) {
                        System.out.println("Underage: Ticket Not Allowed");
                    } else {
                        System.out.println("Ticket Printed Successfully");
                    }
                }
            } else if (choice == 2) {
                System.out.println("Snack purchased.");
            } else if (choice == 3) {
                System.out.println("Exiting...");
            }
        } while (choice != 3);
    }
}

public class Argosino_CinemaTicketTest {
    @Test
    public void testCinemaFlow() {
        StringBuilder automatedInput = new StringBuilder();
        System.out.println("--- GENERATING CINEMA TICKET ---");

        // Step 1: Test underage restriction (< 18)       
        automatedInput.append("1\n"); // Choose Buy Ticket
        automatedInput.append("15\n"); // Enter age 15
        
        // Step 3: Test snack purchase        
        automatedInput.append("2\n"); // Choose Buy Snacks
        
        // Step 4: Exit system
        automatedInput.append("3\n"); // Choose Exit
        System.out.println("--- GENERATION COMPLETE---\n");
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(automatedInput.toString().getBytes());
        Scanner scanner = new Scanner(inputStream);
        
        CinemaTicketSystem cinemaSystem = new CinemaTicketSystem();
        cinemaSystem.start(scanner);
    }
}
