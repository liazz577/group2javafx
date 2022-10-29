package group2JP2.dao.impls;

import group2JP2.dao.interfaces.ITypeOfFilmRepository;
import group2JP2.entities.TypeOfFilm;
import group2JP2.helper.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TypeOfFilmRepository implements ITypeOfFilmRepository {
    @Override
    public ArrayList<TypeOfFilm> all() {
        ArrayList<TypeOfFilm> ls = new ArrayList<>();
        try{
            String sql ="select * from typeoffilms";
            Connector conn = Connector.getInstance();
            ResultSet rs = conn.query(sql);
            while (rs.next()){
                ls.add( new TypeOfFilm(
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

    @Override
    public boolean create(TypeOfFilm typeOfFilm) {
        return false;
    }

    @Override
    public boolean update(TypeOfFilm typeOfFilm) {
        return false;
    }

    @Override
    public boolean delete(TypeOfFilm typeOfFilm) {
        return false;
    }

    @Override
    public TypeOfFilm findOne(Integer id) {
        try{
            String sql ="select * from typeoffilms where tfid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList();
            arr.add(id);
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                return new TypeOfFilm(
                        rs.getInt("tfid"),
                        rs.getString("tfname"),
                        rs.getString("description")
                );
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
