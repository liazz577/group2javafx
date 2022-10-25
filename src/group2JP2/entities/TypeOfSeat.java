package group2JP2.entities;

public class TypeOfSeat {
    public Integer id;
    public String name;

    public TypeOfSeat() {
    }

    public TypeOfSeat(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
