package UI;

import api.HotelResource;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class findARoom {
    private static HotelResource hotelResource = HotelResource.getInstance();
    public static ArrayList<Date> run(Scanner scanner){
        Date checkInDate = selectDate(scanner, "CheckIn");
        Date checkOutDate = selectDate(scanner, "CheckOut");

        if (!getAvailableRooms(checkInDate, checkOutDate).isEmpty()) {
            ArrayList<IRoom> availableRooms = getAvailableRooms(checkInDate, checkOutDate);
            for (IRoom availableRoom: availableRooms){
                System.out.println(availableRoom);
            }

        } else {
            Format f = new SimpleDateFormat("E, MMM dd yyyy");
            System.out.println("No rooms available for the dates: " + f.format(checkInDate) + " to " + f.format(checkOutDate));
            System.out.println("Checking availability in next 7 days");
            ArrayList<IRoom> availableRooms = getAvailableRooms(checkInDate, checkOutDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkInDate);
            calendar.add(calendar.DAY_OF_MONTH, 7);
            Date sevenPlusCheckIn = calendar.getTime();
            while (availableRooms.isEmpty() && checkInDate.before(sevenPlusCheckIn)){
                checkInDate = incrementDateByOne(checkInDate);
                checkOutDate = incrementDateByOne(checkOutDate);
                availableRooms = getAvailableRooms(checkInDate, checkOutDate);
                if (!availableRooms.isEmpty()) {
                    System.out.println("Room Availability Found between: " + f.format(checkInDate) + " and " + f.format(checkOutDate));
                    for (IRoom availableRoom : availableRooms) {
                        System.out.println(availableRoom);
                    }
                }
            }
            if (checkInDate.equals(sevenPlusCheckIn)){
                System.out.println("Sorry, no room available in next 7 days");
            }
        }
        ArrayList<Date> inOutDates = new ArrayList<>();
        inOutDates.add(checkInDate);
        inOutDates.add(checkOutDate);
        return inOutDates;
    }

    public static Date selectDate(Scanner scanner, String dateName){
        boolean keepRunning = true;
        Date date = new Date();
            while (keepRunning) {
                try {
                    System.out.println("Enter " + dateName + "date mm/dd/yyyy example 12/02/2021");
                    date = new SimpleDateFormat("MM/dd/yyyy").parse(scanner.nextLine());
                    keepRunning = false;
                } catch (IllegalArgumentException ex) {
                    System.out.println("please enter the date in correct format");
                } catch (Exception ex) {
                    ex.getLocalizedMessage();
            }
        }
        return date;
    }

    public static ArrayList<IRoom> getAvailableRooms(Date checkInDate, Date checkOutDate){
        ArrayList<IRoom> availableRooms = new ArrayList<>();
        availableRooms.addAll(hotelResource.findARoom(checkInDate, checkOutDate));
        return availableRooms;
    }

    public static Date incrementDateByOne(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }
}
