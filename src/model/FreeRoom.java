package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType RoomType){
        super(roomNumber, 0.0, RoomType);
    }

    @Override
    public boolean isFree(){
        return true;
    }

    @Override
    public String toString(){
        return "Free-Room { " + "Number: " + roomNumber + ",Type: " + RoomType + "}";
    }

}
