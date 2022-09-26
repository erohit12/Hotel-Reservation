package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AdminResource {
    private static AdminResource INSTANCE;
    private AdminResource(){}
    public static AdminResource getInstance(){
        if (INSTANCE == null)
            INSTANCE = new AdminResource();
        return INSTANCE;
    }
    private static ReservationService reservationService = ReservationService.getInstance();
    private static CustomerService customerService = CustomerService.getInstance();
    private static Set<IRoom> rooms = reservationService.getRooms();

    public static Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public static void addRoom(Set<IRoom> rooms){
        for(IRoom room: rooms){
            reservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms(){
        return rooms;
    }

    public static Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    public static void displayAllReservations(){
        reservationService.printAllReservations();
    }

}
