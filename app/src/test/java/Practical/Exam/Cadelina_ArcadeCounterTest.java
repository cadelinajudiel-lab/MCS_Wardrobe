package  com.example.myapplication.practical.exam;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Cadelina_ArcadeCounterTest {

    @Test
    public void testArcadeFlow() {

        StringBuilder automatedInput = new StringBuilder();

        System.out.println("=== GENERATING ARCADE TEST DATA ===");

        // Step 1: Buy tokens pang laro
        automatedInput.append("1\n"); // Choose Buy Tokens

        // Step 2: Test low ticket count kung ilan
        automatedInput.append("2\n"); // Choose Claim Prize
        automatedInput.append("20\n"); // Enter 20 tickets
        // Expected: Keep Playing

        // Step 3: Test high ticket count kung ilan
        automatedInput.append("2\n"); // Choose Claim Prize
        automatedInput.append("600\n"); // Enter 600 tickets
        // Expected: Teddy Bear Won

        // Step 4: Exit
        automatedInput.append("3\n"); // Choose Exit

        System.out.println("=== TEST DATA GENERATION COMPLETE ===\n");

        // Use the test data as keyboard input
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(automatedInput.toString().getBytes());

        Scanner scanner = new Scanner(inputStream);

        ArcadeMenu arcadeSystem = new ArcadeMenu();
        arcadeSystem.start(scanner);
    }
}
