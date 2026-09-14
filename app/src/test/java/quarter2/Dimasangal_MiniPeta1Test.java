package quarter2;

import org.junit.Test;

public class Dimasangal_MiniPeta1Test {
    @Test
    public void testPrintMyProfile() {
        // Personal details

        String myName = "Denise";
        String petName = "Lucky";
        String favFood = "Takoyaki";
        int myAge = 16;

        // Display profile
        System.out.println("===== MY DIGITAL PROFILE =====");

        System.out.println(
                "Hello, my name is " + myName +
                        " and I am " + myAge + " years old."
        );

        System.out.println(
                "I have a wonderful pet named " + petName + "."
        );

        System.out.println(
                "If I could, I would eat " + favFood +
                        " every single day!"
        );
    }
}
