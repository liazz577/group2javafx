package group2JP2.dao.impls;

import group2JP2.dao.interfaces.IMovieTicketRepository;
import group2JP2.entities.Film;
import group2JP2.entities.MovieTicket;
import group2JP2.entities.ShowTime;
import group2JP2.helper.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MovieTicketRepository implements IMovieTicketRepository {
    @Override
    public ArrayList<MovieTicket> all() {
        ArrayList<MovieTicket> ls = new ArrayList<>();
        try{
            String sql = "select * from movietickets";
            Connector conn = Connector.getInstance();
            ResultSet rs = conn.query(sql);
            while (rs.next()){
                ls.add(new MovieTicket(
                        rs.getInt("id"),
                        rs.getFloat("price"),
                        rs.getInt("fid"),
                        rs.getInt("seid"),
                        rs.getInt("showid"),
                        rs.getInt("oid")
                ));

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return ls;
    }

    @Override
    public boolean create(MovieTicket movieTicket) {
        try{
            String sql ="insert into movietickets(price,fid,seid,showid,oid) values (?,?,?,?,?)";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList();
            arr.add(movieTicket.getPrice());
            arr.add(movieTicket.getFilmId());
            arr.add(movieTicket.getSeatId());
            arr.add(movieTicket.getShowTimeId());
            arr.add(movieTicket.getOrderId());
            if(conn.execute(sql,arr)){
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(MovieTicket movieTicket) {
        try{
            String sql = "update movietickets set price =?, fid =?, seid =?, showid =?, oid =? where id =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList();
            arr.add(movieTicket.getPrice());
            arr.add(movieTicket.getFilmId());
            arr.add(movieTicket.getSeatId());
            arr.add(movieTicket.getShowTimeId());
            arr.add(movieTicket.getOrderId());
            arr.add(movieTicket.getId());
            if(conn.execute(sql,arr)){
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(MovieTicket movieTicket) {
        try{
            String sql ="delete from movietickets where id =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList();
            arr.add(movieTicket.getId());
            if(conn.execute(sql,arr)){
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public MovieTicket findOne(Integer id) {
        try{
            String sql ="select * from movietickets where id =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList();
            arr.add(id);
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                return new MovieTicket(
                        rs.getInt("id"),
                        rs.getFloat("price"),
                        rs.getInt("fid"),
                        rs.getInt("seid"),
                        rs.getInt("showid"),
                        rs.getInt("oid")
                );
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<MovieTicket> findFilmShow(Film film, ShowTime showTime){
        ArrayList<MovieTicket> ls = new ArrayList<>();
        try{
            String sql="select * from movietickets where fid=? and showid=?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList();
            arr.add(film.getId());
            arr.add(showTime.getId());
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                ls.add(new MovieTicket(
                        rs.getInt("id"),
                        rs.getFloat("price"),
                        rs.getInt("fid"),
                        rs.getInt("seid"),
                        rs.getInt("showid"),
                        rs.getInt("oid")
                ));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }
}
