package UI;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.ReservationService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Admin {
    private static AdminResource adminResource = AdminResource.getInstance();
    public static void run(Scanner scanner){

        boolean keepRunning = true;
        while (keepRunning){
            System.out.println("\nAdmin Menu");
            System.out.println("__________________________________");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            System.out.println("___________________________________");
            try {
                int selection = Integer.parseInt(scanner.nextLine());
                switch (selection){
                    case 1:
                        for (Customer customer: adminResource.getAllCustomers()){
                            System.out.println(customer);
                        }
                        break;
                    case 2:
                        for (IRoom room: adminResource.getAllRooms()){
                            System.out.println(room);
                        }
                        break;
                    case 3:
                        adminResource.displayAllReservations();
                        break;
                    case 4:
                        Set<IRoom> newRooms = new HashSet<>();
                        System.out.println("Enter room number: ");
                        String roomNumber = "";
                        Double price = 0.0;
                        RoomType roomType = RoomType.Single;
                        try {
                            roomNumber = scanner.nextLine();
                            boolean stopRunning = false;
                            for (IRoom room : adminResource.getAllRooms()) {
                                if (room.getRoomNumber().equals(roomNumber)) {
                                    System.out.println("The room with this room number already present");
                                    stopRunning = true;
                                    break;
                                }
                            }
                            if (!stopRunning) {
                                System.out.println("Enter price per night");
                                price = Double.parseDouble(scanner.nextLine());
                                int roomTypeNumber = 0;
                                do {
                                    System.out.println("Enter room type: 1 for single, 2 for double");
                                    roomTypeNumber = Integer.parseInt(scanner.nextLine());
                                } while (!(roomTypeNumber == 1 || roomTypeNumber == 2));
                                roomType = roomTypeNumber == 1 ? RoomType.Single : RoomType.Double;
                            }
                        }catch (Exception ex){
                                ex.getLocalizedMessage();
                            }
                        newRooms.add(new Room(roomNumber, price, roomType));
                        adminResource.addRoom(newRooms);
                        break;
                    case 5:
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("Enter a number between 1 and 5 (Inclusive)");
                }
            } catch (IllegalArgumentException ex){
                System.out.println("Enter a number between 1 and 5 (Inclusive)");
            }
        }
    }
}
