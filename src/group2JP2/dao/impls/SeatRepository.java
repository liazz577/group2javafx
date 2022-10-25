package group2JP2.dao.impls;




import group2JP2.dao.interfaces.ISeatRepository;
import group2JP2.entities.Seat;
import group2JP2.helper.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SeatRepository implements ISeatRepository {
    @Override
    public ArrayList<Seat> all() {
        ArrayList<Seat> ls = new ArrayList<>();
        try{
            String sql ="select * from seats";
            Connector conn = Connector.getInstance();
            ResultSet rs = conn.query(sql);
            while (rs.next()){
                ls.add(new Seat(
                        rs.getInt("sid"),
                        rs.getString("sname"),
                        rs.getInt("tsid"),
                        rs.getInt("rid")
                        ));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }

    @Override
    public boolean create(Seat seat) {
        return false;
    }

    @Override
    public boolean update(Seat seat) {
        return false;
    }

    @Override
    public boolean delete(Seat seat) {
        return false;
    }

    @Override
    public Seat findOne(Integer id) {
        try{
            String sql ="select * from seats where sid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            arr.add(id);
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                return new Seat(
                        rs.getInt("sid"),
                        rs.getString("sname"),
                        rs.getInt("tsid"),
                        rs.getInt("rid")
                );
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
