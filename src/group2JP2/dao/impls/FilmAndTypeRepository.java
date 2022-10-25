package group2JP2.dao.impls;

import group2JP2.dao.interfaces.IFilmAndTypeRepository;
import group2JP2.entities.FilmAndType;
import group2JP2.helper.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class FilmAndTypeRepository implements IFilmAndTypeRepository {
    @Override
    public ArrayList<FilmAndType> all() {
        ArrayList<FilmAndType> ls = new ArrayList<>();
        try{
            String sql = "select * form filmsandtype";
            Connector conn = Connector.getInstance();
            ResultSet rs = conn.query(sql);
            while (rs.next()){
                ls.add(new FilmAndType(rs.getInt("fid"),rs.getInt("tfid")));

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }

    @Override
    public boolean create(FilmAndType filmAndType) {
        return false;
    }

    @Override
    public boolean update(FilmAndType filmAndType) {
        return false;
    }

    @Override
    public boolean delete(FilmAndType filmAndType) {
        return false;
    }

    @Override
    public FilmAndType findOne(Integer id) {
        return null;
    }
}
