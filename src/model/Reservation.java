package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    final Customer customer;
    final IRoom room;
    Date checkInDate = new Date();
    Date checkOutDate = new Date();

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public Customer getCustomer(){
        return customer;
    }
    public IRoom getRoom(){
        return room;
    }

    public Date getCheckInDate(){
        return checkInDate;
    }

    public Date getCheckOutDate(){
        return  checkOutDate;
    }

    @Override
    public String toString(){
        return  "Customer: " + customer.toString() + "\nRoom: " + room.getRoomNumber() + " - " + room.getRoomType() + "bed" + "Room price: $" + room.getRoomPrice() + "price per night" + "\nCheck-In: " + checkInDate + "\nCheck-Out: " + checkOutDate ;
    }

    @Override
    public int hashCode(){
        return Objects.hash(room,checkInDate, checkOutDate);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return reservation.getRoom().equals(room)
                && reservation.getCheckInDate().equals(checkInDate)
                && reservation.getCheckOutDate().equals(checkOutDate);
    }
}
