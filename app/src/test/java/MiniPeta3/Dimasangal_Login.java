package minipeta3;

import org.junit.Test;

public class Dimasangal_Login {

    @Test

    public void main() {
        boolean isReadyToLogin = true;

        // Stores username
        String username = "DeniseDimasangal";

        // Stores password
        String password = "DD92710";

        if (isReadyToLogin) {
            System.out.println("Login Successful");
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        } else {
            System.out.println("Login Failed");
        }
    }
}
