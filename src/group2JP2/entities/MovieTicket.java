package group2JP2.entities;


import group2JP2.dao.impls.FilmRepository;
import group2JP2.dao.impls.RoomRepository;
import group2JP2.dao.impls.SeatRepository;
import group2JP2.dao.impls.ShowTimeRepository;
import group2JP2.enums.RepoType;
import group2JP2.factory.RepositoryFactory;
import javafx.scene.control.Button;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MovieTicket {
    public Integer id;
    public Float price;
    public Integer filmId;
    public Integer showTimeId;
    public Integer seatId;
    public Integer orderId;
    public String nameFilm;
    public String nameSeat;
    public String nameRoom;
    public String startShow;
    public String endShow;
    public Button choose;
    public static ArrayList<MovieTicket> selectMovieTicket = new ArrayList<>();

    public MovieTicket() {
    }

    public MovieTicket(Integer id, Float price, Integer filmId, Integer seatId , Integer showTimeId , Integer orderId) {
        this.id = id;
        this.price = price;
        this.filmId = filmId;
        this.showTimeId = showTimeId;
        this.seatId = seatId;
        this.orderId = orderId;
        this.choose = new Button("AddToBag");
        this.choose.setOnAction(event -> {
            try{
                selectMovieTicket.add(this);
                this.choose.setVisible(false);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

    }

    public Integer getId() {
        return id;
    }


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getFilmId() {
        return filmId;
    }


    public Integer getShowTimeId() {
        return showTimeId;
    }


    public ShowTime showTime(){
        ShowTimeRepository str = new ShowTimeRepository();
        return str.findOne(this.getShowTimeId());
    }
    public Film film(){
        FilmRepository fr = new FilmRepository();
        return fr.findOne(this.getFilmId());
    }

    public LocalDateTime getStartShow(){
        return this.showTime().getStartShow();
    }
    public LocalDateTime getEndShow(){
        return this.showTime().getEndShow();
    }

    public Integer getSeatId() {
        return seatId;

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getNameFilm() {
        return this.film().getName();
    }

    public Seat seat(){
        SeatRepository sr = new SeatRepository();
        return sr.findOne(this.getSeatId());
    }

    public String getNameSeat() {
        return this.seat().getName();
    }
    public Room room(){
        RoomRepository rr = new RoomRepository();
        return rr.findOne(this.seat().getRoomId());
    }

    public String getNameRoom() {
        return this.room().getName();
    }

    public Button getChoose() {
        return choose;
    }
}
