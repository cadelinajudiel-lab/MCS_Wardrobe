package quarter2.MiniPeta3;
import java.util.Scanner;
public class Cadelina_PlaceOrder {

    public class Main {

        public void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            String item;
            int quantity;
            double price;
            double total;

            System.out.println("===== PLACE ORDER SYSTEM =====");

            System.out.print("Enter Item Name: ");
            item = sc.nextLine();

            System.out.print("Enter Quantity: ");
            quantity = sc.nextInt();

            System.out.print("Enter Price: ");
            price = sc.nextDouble();

            total = quantity * price;

            System.out.println("\n===== ORDER RECEIPT =====");
            System.out.println("Item      : " + item);
            System.out.println("Quantity  : " + quantity);
            System.out.println("Price     : " + price);
            System.out.println("Total     : " + total);
            System.out.println("Order Placed Successfully!");

            sc.close();
        }
    }



}
