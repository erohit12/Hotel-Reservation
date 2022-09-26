package model;

import java.util.Objects;

public class Room implements IRoom{
    final String roomNumber;
    Double price;
    final RoomType RoomType;
    public Room(String roomNumber, Double roomPrice, RoomType RoomType){
        this.roomNumber = roomNumber;
        this.price = roomPrice;
        this.RoomType = RoomType;
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    public Double getRoomPrice(){
        return price;
    }

    public RoomType getRoomType(){
        return RoomType;
    }

    public boolean isFree(){
        return false;
    }

    public void setRoomPrice(Double roomPrice){
        this.price = roomPrice;
    }

    @Override
    public String toString(){
        return "Room Number: " + roomNumber + " " + RoomType + " Bed " + "Room Price: $" + price ;
    }

    @Override
    public int hashCode(){
        return Objects.hash(roomNumber);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return room.getRoomNumber().equals(roomNumber);
    }

}
