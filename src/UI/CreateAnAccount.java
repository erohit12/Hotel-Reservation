package UI;

import api.HotelResource;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CreateAnAccount {
    private static HotelResource hotelResource = HotelResource.getInstance();
    static String ReGex = "^(.+)@(.+).com$";
    static Pattern pattern = Pattern.compile(ReGex);
    public static void run(Scanner scanner){
        try {
            boolean keepRunning = true;
            System.out.println("Enter Email format: name@domain.com: ");
            String email = "";
            while (keepRunning) {
                email = scanner.nextLine();
                if (pattern.matcher(email).matches()) {
                    keepRunning = false;
                } else {
                    System.out.println("Invalid email format");
                    System.out.println("Re-Enter email format: name@domain.com");
                }
            }
            System.out.println("First Name ");
            String firstName = scanner.nextLine();
            System.out.println("Last Name ");
            String lastName = scanner.nextLine();
            hotelResource.createACustomer(email, firstName, lastName);
            } catch (Exception ex) {
            ex.getLocalizedMessage();
        }
    }
}
