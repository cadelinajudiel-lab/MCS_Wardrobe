package quarter2;

import org.junit.Test

class Lao_MiniPeta1Test {
    @Test
    fun printMyProfile() {
        // Personal details

        val myName = "Lao"
        val petName = "Greyham"
        val favFood = "Kare-Kare"
        val myAge = 16

        // Display profile
        println("===== MY DIGITAL PROFILE =====")

        println(
                "Hello, my name is " + myName +
                        " and I am " + myAge + " years old."
        )

        println(
                "I have a wonderful pet named " + petName + "."
        )

        println(
                "If I could, I would eat " + favFood +
                        " every single day!"
        )
    }
}