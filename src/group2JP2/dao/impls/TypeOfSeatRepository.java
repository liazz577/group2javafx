package group2JP2.dao.impls;

import group2JP2.dao.interfaces.ITypeOfSeatRepository;
import group2JP2.entities.TypeOfSeat;
import group2JP2.helper.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TypeOfSeatRepository implements ITypeOfSeatRepository {
    @Override
    public ArrayList<TypeOfSeat> all() {
        ArrayList<TypeOfSeat> ls = new ArrayList<>();
        try{
            String sql ="select * from typeofseats";
            Connector conn = Connector.getInstance();
            ResultSet rs = conn.query(sql);
            while (rs.next()){
                ls.add(new TypeOfSeat(
                        rs.getInt("tsid"),
                        rs.getString("tsname")
                ));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }

    @Override
    public boolean create(TypeOfSeat typeOfSeat) {
        return false;
    }

    @Override
    public boolean update(TypeOfSeat typeOfSeat) {
        return false;
    }

    @Override
    public boolean delete(TypeOfSeat typeOfSeat) {
        return false;
    }

    @Override
    public TypeOfSeat findOne(Integer id) {
        try{
            String sql ="select * from typeofseats where tsid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            arr.add(id);
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                return new TypeOfSeat(
                        rs.getInt("tsid"),
                        rs.getString("tsname")
                );
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
