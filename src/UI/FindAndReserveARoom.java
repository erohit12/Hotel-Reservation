package UI;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class FindAndReserveARoom {
    private static HotelResource hotelResource = HotelResource.getInstance();
    static Scanner scanner = new Scanner(System.in);
    static String email = "";
    static String roomNumber = "";
    public static void run(Scanner scanner) {
        ArrayList<Date> inOutDate = new ArrayList<>();
        inOutDate.addAll(findARoom.run(scanner));
        Date checkInDate = inOutDate.get(0);
        Date checkOutDate = inOutDate.get(1);
        String isBooking = "";
        if (!HotelResource.findARoom(checkInDate,checkOutDate).isEmpty()) {
            System.out.println("Checkin: " + checkInDate);
            System.out.println("Checkout: "+ checkOutDate);
            System.out.println("Would you like to book a room? y/n");
            try {
                isBooking = scanner.nextLine();
                isBooking = gettingYesNo(scanner, isBooking);
            } catch (Exception ex) {
                ex.getLocalizedMessage();
            }
        }
        String hasAccount = "";
        if (isBooking.equalsIgnoreCase("Y")) {
            boolean isRoomAvailable = false;
            System.out.println("Do you have an account with us? y/n");
            try {
                hasAccount = scanner.nextLine();
                hasAccount = gettingYesNo(scanner, hasAccount);
            } catch (Exception ex) {
                ex.getLocalizedMessage();
            }

            if (hasAccount.equalsIgnoreCase("Y")) {
                System.out.println("Enter Email format name@domain.com ");
                email = scanner.nextLine();
                Customer customer = hotelResource.getCustomer(email);
                String isEmailRetry = "Y";
                while (Objects.isNull(customer) && isEmailRetry.equalsIgnoreCase("Y")) {
                    System.out.println("No such email found");
                    System.out.println("Would you like to retry? y/n");
                    isEmailRetry = scanner.nextLine();
                    isEmailRetry = gettingYesNo(scanner, isEmailRetry);
                    if (isEmailRetry.equalsIgnoreCase("Y")) {
                        System.out.println("Enter Email format: ");
                        email = scanner.nextLine();
                        customer = hotelResource.getCustomer(email);
                    }
                }
                if (!Objects.isNull(customer)) {
                    System.out.println("Enter room you would like to reserve");
                    roomNumber = scanner.nextLine();
                    String isRetryRoomNumber = "Y";
                    ArrayList<IRoom> availableRooms = new ArrayList<>();
                    availableRooms.addAll(hotelResource.findARoom(checkInDate, checkOutDate));
                    for (IRoom room : availableRooms) {
                        if (room.getRoomNumber().equals(roomNumber)) {
                            isRoomAvailable = true;
                        }
                    }

                    while (!isRoomAvailable && isRetryRoomNumber.equalsIgnoreCase("Y")) {
                        System.out.println("No such room Number Found");
                        System.out.println("Please recheck the list of Available rooms");
                        for (IRoom room : hotelResource.findARoom(checkInDate, checkOutDate)) {
                            System.out.println(room);
                        }
                        System.out.println("Would you like to retry? y/n");
                        isRetryRoomNumber = scanner.nextLine();
                        isRetryRoomNumber = gettingYesNo(scanner, isRetryRoomNumber);
                        if (isRetryRoomNumber.equalsIgnoreCase("Y")){
                            System.out.println("Enter room Number");
                            roomNumber = scanner.nextLine();
                            for (IRoom room : availableRooms) {
                                if (room.getRoomNumber().equals(roomNumber)) {
                                    isRoomAvailable = true;
                                    break;
                                }
                            }
                        }

                    }
                }
            }

            if (isRoomAvailable) {
                Reservation newReservation = hotelResource.bookARoom(email, HotelResource.getRoom(roomNumber), checkInDate, checkOutDate);
                System.out.println("Reservation");
                Format f = new SimpleDateFormat("E, MMM dd yyyy");
                String checkIn = f.format(checkInDate);
                String checkOut = f.format(checkOutDate);
                Customer customer = hotelResource.getCustomer(email);
                IRoom room = hotelResource.getRoom(roomNumber);
                System.out.println(customer.getName());
                System.out.println("Room: " + roomNumber + " - " + room.getRoomType());
                System.out.println("Price: $" + room.getRoomPrice());
                System.out.println("Checkin Date: " + checkIn);
                System.out.println("Checkout Date: " + checkOut);
            }
        }
    }


    public static String gettingYesNo(Scanner scanner, String choice){
        while(!(choice.equalsIgnoreCase("Y") | choice.equalsIgnoreCase("N"))){
            try {
                System.out.println("Please Enter y Yes ,n No ");
                choice = scanner.nextLine();
            } catch(Exception ex){
                ex.getLocalizedMessage();
            }
        }
        return choice;
    }

}
