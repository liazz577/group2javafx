package group2JP2.dao.impls;

import group2JP2.dao.interfaces.IShowTimeRepository;
import group2JP2.entities.ShowTime;
import group2JP2.helper.Connector;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShowTimeRepository implements IShowTimeRepository {
    @Override
    public ArrayList<ShowTime> all() {
        ArrayList<ShowTime> ls = new ArrayList<>();
        try{
            String sql ="select * from showtimes";
            Connector conn = Connector.getInstance();
            ResultSet rs = conn.query(sql);
            while (rs.next()){
                String st = rs.getString("startshow");
                String s1 =st.substring(0,10);
                String s2 = st.substring(11,19);
                String s = s1+"T"+s2;
                String se = rs.getString("endshow");
                String s3 =se.substring(0,10);
                String s4 = se.substring(11,19);
                String q =s3+"T"+s4;


                ls.add(new ShowTime(
                        rs.getInt("sid"),
                        LocalDateTime.parse(s),
                        LocalDateTime.parse(q))
                );
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }

    @Override
    public boolean create(ShowTime showTime) {
        return false;
    }

    @Override
    public boolean update(ShowTime showTime) {
        return false;
    }

    @Override
    public boolean delete(ShowTime showTime) {
        return false;
    }

    @Override
    public ShowTime findOne(Integer id) {
        try{
            String sql ="select * from showtimes where sid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            arr.add(id);
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                String st = rs.getString("startshow");
                String s1 =st.substring(0,10);
                String s2 = st.substring(11,19);
                String s = s1+"T"+s2;
                String se = rs.getString("endshow");
                String s3 =se.substring(0,10);
                String s4 = se.substring(11,19);
                String q =s3+"T"+s4;
                return new ShowTime(
                        rs.getInt("sid"),
                        LocalDateTime.parse(s),
                        LocalDateTime.parse(q)
                );
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
