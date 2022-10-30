package group2JP2.entities;

public class Seat {
    public Integer id;
    public String name;
    public Integer tsId;
    public Integer roomId;


    public Seat() {
    }

    public Seat(Integer id, String name, Integer tsId, Integer roomId) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
        this.tsId = tsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }
}
