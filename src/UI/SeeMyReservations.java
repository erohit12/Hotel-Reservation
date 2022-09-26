package UI;

import api.HotelResource;
import model.Reservation;

import java.util.Scanner;
import java.util.regex.Pattern;

public class SeeMyReservations {
    private static HotelResource hotelResource = HotelResource.getInstance();
    public static void run(Scanner scanner){
        boolean keepRunning = true;
        System.out.println("Enter your email in name@email.com format: ");
        while (keepRunning){
            String ReGex = "^(.+)@(.+).com$";
            Pattern pattern = Pattern.compile(ReGex);
            try {
                String email = scanner.nextLine();
                if (pattern.matcher(email).matches()){
                    System.out.println("Your Reservations are: \n");
                    for (Reservation reservation: hotelResource.getCustomerReservation(email)){
                        System.out.println(reservation.getRoom());
                        System.out.println(reservation.getCheckInDate());
                        System.out.println(reservation.getCheckOutDate());
                    }
                    keepRunning = false;
                } else {
                    System.out.println("Invalid Email");
                    System.out.println("Retry in name@domain.com format");
                }

            } catch (Exception ex){
                ex.getLocalizedMessage();
            }
        }
    }

}
