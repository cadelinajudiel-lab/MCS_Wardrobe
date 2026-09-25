package quarter2;

import org.junit.Test;

public class Argosino_MiniPeta1 {1
    @Test
    public void testPrintMyProfile() {
        // Personal details
        String myName = "Jai";
        String petName = "Kobie";
        String favFood = "Bread";
        int myAge = 16;

        // Display profile
        System.out.println("===== MY DIGITAL PROFILE =====");
        System.out.println("Hello, my name is " + myName + " and I am " + myAge + " years old.");
        System.out.println("I have a wonderful pet named " + petName + ".");
        System.out.println("If I could, I would eat " + favFood + " every single day!");
    }
}
