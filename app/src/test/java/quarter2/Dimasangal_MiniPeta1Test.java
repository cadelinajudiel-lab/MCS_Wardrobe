package quarter2;

import org.junit.Test;



    @Test
    public void testProfile() {
        // 1. THE INPUT (Storing your personal details in variables)
        String myName = "Denise";
        String petName = "Lucky";
        String favFood = "Takoyaki";
        int myAge = 15;

        // 2. THE OUTPUT (Printing to the console)
        System.out.println("--- MY DIGITAL PROFILE ---");
        System.out.println("Hello, my name is " + myName + " and I am " + myAge + " years old.");
        System.out.println("I have a wonderful pet named " + petName + ".");
        System.out.println("If I could, I would eat " + favFood + " every single day!");
    }
}