package group2JP2.factory;

import group2JP2.dao.impls.*;
import group2JP2.dao.interfaces.IRepository;
import group2JP2.enums.RepoType;

public class RepositoryFactory {
    private RepositoryFactory(){

    }
    public static IRepository creatRepository(RepoType type){
        switch (type){
            case FILM: return new FilmRepository();
            case MOVIETICKET:return new MovieTicketRepository();
            case FILMANDTYPE: return new FilmAndTypeRepository();
            case ORDERTICKET: return new OrderTicketRepository();
            case ROOM: return new RoomRepository();
            case SEAT: return new SeatRepository();
            case SHOWTIME: return new ShowTimeRepository();
            case TYPEOFFILM: return new TypeOfFilmRepository();
            case TYPEOFSEAT: return new TypeOfSeatRepository();
            default: throw new IllegalArgumentException("Thiếu tham số rồi");
        }

    }
}
