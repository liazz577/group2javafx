package group2JP2.dao.impls;

import group2JP2.dao.interfaces.IRoomRepository;
import group2JP2.entities.Room;
import group2JP2.helper.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RoomRepository implements IRoomRepository {
    @Override
    public ArrayList<Room> all() {
        ArrayList<Room> ls = new ArrayList<>();
        try{
            String sql ="select * from rooms";
            Connector conn = Connector.getInstance();
            ResultSet rs = conn.query(sql);
            while (rs.next()){
                ls.add(new Room(
                        rs.getInt("rid"),
                        rs.getString("rname"),
                        rs.getString("rdescription"),
                        rs.getInt("qtyseats")
                ));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }

    @Override
    public boolean create(Room room) {
        return false;
    }

    @Override
    public boolean update(Room room) {
        return false;
    }

    @Override
    public boolean delete(Room room) {
        return false;
    }

    @Override
    public Room findOne(Integer id) {
        try{
            String sql ="select * from rooms where rid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            arr.add(id);
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                return new Room(
                        rs.getInt("rid"),
                        rs.getString("rname"),
                        rs.getString("rdescription"),
                        rs.getInt("qtyseats")
                );
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
