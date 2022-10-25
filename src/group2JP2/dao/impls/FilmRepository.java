package group2JP2.dao.impls;

import group2JP2.dao.interfaces.IFilmRepository;
import group2JP2.entities.Film;
import group2JP2.entities.ShowTime;
import group2JP2.entities.TypeOfFilm;
import group2JP2.helper.Connector;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FilmRepository implements IFilmRepository {
    @Override
    public ArrayList<Film> all() {
        ArrayList<Film> ls = new ArrayList<>();
        try{
            String sql = "select * from films";
            Connector conn = Connector.getInstance();
            ResultSet rs = conn.query(sql);
            while (rs.next()){
                ls.add(new Film(rs.getInt("fid"),
                        rs.getString("fname"),
                        rs.getString("author"),
                        rs.getInt("time"),
                        rs.getString("content")
                        ));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }

    @Override
    public boolean create(Film film) {
        try{
            String sql ="insert into films(fname,author,time,content) values(?,?,?,?)";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            arr.add(film.getName());
            arr.add(film.getAuthor());
            arr.add(film.getTime());
            arr.add(film.getContent());
            if(conn.execute(sql,arr)){
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Film film) {
        try{
            String sql ="update films set fname =?, author =?, time =?,content =? where fid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            arr.add(film.getName());
            arr.add(film.getAuthor());
            arr.add(film.getTime());
            arr.add(film.getContent());
            arr.add(film.getId());
            if(conn.execute(sql,arr)){
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Film film) {
        try{
            String sql ="delete form films where fid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            arr.add(film.getId());
            if(conn.execute(sql,arr)){
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Film findOne(Integer id) {
        try{
            String sql = "select * from films where fid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                return new Film(rs.getInt("fid"),
                        rs.getString("fname"),
                        rs.getString("author"),
                        rs.getInt("time"),
                        rs.getString("content")
                );
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public ArrayList<TypeOfFilm> findType(Film film){
        ArrayList<TypeOfFilm> ls = new ArrayList<>();
        try{
            String sql ="select * from typeoffilms where tfid in (select tfid from filmsandtype where fid =?)";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            arr.add(film.getId());
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                ls.add(new TypeOfFilm(
                        rs.getInt("tfid"),
                        rs.getString("tfname"),
                        rs.getString("description")
                ));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }

    public ArrayList<ShowTime> findShow(Film film){
        ArrayList<ShowTime> ls = new ArrayList<>();
        try{
            String sql ="select * from showtimes where sid in (select showid from movietickets where fid =?)";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList<>();
            arr.add(film.getId());
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
                ls.add(new ShowTime(
                        rs.getInt("sid"),
                        LocalDateTime.parse(s),
                        LocalDateTime.parse(q)));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;

    }


}
