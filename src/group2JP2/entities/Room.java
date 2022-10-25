package group2JP2.entities;

public class Room {
    public Integer id;
    public String name;
    public String description;
    public Integer qtySeat;

    public Room() {
    }

    public Room(Integer id, String name, String description, Integer qtySeat) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qtySeat = qtySeat;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQtySeat() {
        return qtySeat;
    }

    public void setQtySeat(Integer qtySeat) {
        this.qtySeat = qtySeat;
    }
}
