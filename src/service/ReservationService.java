package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService INSTANCE;

    private ReservationService(){}

    public static ReservationService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ReservationService();
        }

        return INSTANCE;
    }
    static Set<IRoom> rooms = new HashSet<>();
    static Set<Reservation> reservations = new HashSet<>();

    public static Set<IRoom> getRooms(){
        if (rooms == null)
            rooms = new HashSet<>();

        return rooms;
    }

    static HashMap<IRoom, Reservation> roomToReservation = new HashMap<>();

    public static void addRoom(IRoom room){
        rooms.add(room);
    }

    public static IRoom getARoom(String roomId){
        for(IRoom room: rooms){
            if (room.getRoomNumber().equals(roomId)){
                return room;
            }
        }
        return null;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        for (Reservation checkReservation:reservations){
            if (checkReservation.equals(reservation)){
                return null;
            }
        }
        roomToReservation.put(room, reservation);
        extendReservations(reservation);
        return reservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        ArrayList<IRoom> freeRooms = new ArrayList<>();
        for(IRoom room: rooms){
            if (!roomToReservation.keySet().contains(room)){
                freeRooms.add(room);
            } else {
                boolean isAddRoom = false;
                for(Reservation reservation:reservations){
                    if (reservation.getRoom().getRoomNumber().equals(room.getRoomNumber())){
                        isAddRoom = true;
                        if ((checkInDate.equals(reservation.getCheckInDate()))
                                || ((checkInDate).equals(reservation.getCheckOutDate()))
                                || ((checkInDate).after(reservation.getCheckInDate()) && (checkInDate).before(reservation.getCheckOutDate()))){
                            isAddRoom = false;
                            break;
                        } else if ((checkOutDate.equals(reservation.getCheckInDate()))
                            || (checkOutDate.equals(reservation.getCheckOutDate()))
                            || ((checkOutDate.after(reservation.getCheckInDate())) && (checkOutDate.before(reservation.getCheckOutDate())))){
                            isAddRoom = false;
                            break;
                        } else if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate())){
                            isAddRoom = false;
                            break;
                        }
                    }
                }
                if (isAddRoom){
                    freeRooms.add(room);
                }
            }
        }
        return freeRooms;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        ArrayList<Reservation> customersReservations = new ArrayList<>();
        for(Reservation reservation: reservations){
            if(reservation.getCustomer().equals(customer)){
                customersReservations.add(reservation);
            }
        }
        return customersReservations;
    }

    public static void printAllReservations(){
        for(Reservation reservation: reservations){
            System.out.println(reservation);
        }
    }

    static void extendReservations(Reservation reservation)
    {
        reservations.add(reservation);
    }


}
