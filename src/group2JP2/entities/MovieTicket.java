package group2JP2.entities;


import group2JP2.Main;
import group2JP2.dao.impls.*;
import group2JP2.enums.RepoType;
import group2JP2.factory.RepositoryFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MovieTicket {
    public Integer id;
    public Integer price;
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
    public Button delete;
    public Button deleteOutOrder;
    public static ArrayList<MovieTicket> selectMovieTicket = new ArrayList<>();

    public MovieTicket() {
    }

    public MovieTicket(Integer id, Integer price, Integer filmId, Integer seatId , Integer showTimeId , Integer orderId) {
        this.id = id;
        this.price = price;
        this.filmId = filmId;
        this.showTimeId = showTimeId;
        this.seatId = seatId;
        this.orderId = orderId;
        this.delete = new Button("Delete");
        this.choose = new Button("AddToBag");
        this.deleteOutOrder = new Button("DeleteOut");
        this.choose.setOnAction(event -> {
            try{
                int k=0;
                for(MovieTicket s:selectMovieTicket){
                    if(s.getId()==this.getId()){
                        k++;
                    }
                }
                if(k==0){
                    selectMovieTicket.add(this);
                }
                this.choose.setVisible(false);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        this.delete.setOnAction(event -> {
            try{
                selectMovieTicket.remove(this);
                this.delete.setVisible(false);
                Parent re = FXMLLoader.load(getClass().getResource("../orderticket/create/create.fxml"));
                Main.movieStage.setTitle("Create Order");
                Main.movieStage.setScene(new Scene(re,Main.width,Main.height));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });

        this.deleteOutOrder.setOnAction(event -> {
            try{
                if(OrderTicket.editedOrder!=null);{
                    this.deleteOutOrder.setVisible(false);
                    this.setOrderId(Integer.valueOf(0));
                    MovieTicketRepository mtr = new MovieTicketRepository();
                    if(mtr.update(this)){
                        System.out.println("Success");
                    }else {
                        System.out.println("Error");
                    }
                    OrderTicketRepository otr = new OrderTicketRepository();
                    OrderTicket.editedOrder.setQty(OrderTicket.editedOrder.getQty()-1);
                    OrderTicket.editedOrder.setTotalMoney(OrderTicket.editedOrder.getTotalMoney()-this.getPrice());
                    if(otr.update(OrderTicket.editedOrder)){
                        System.out.println("Done");
                    }else{
                        System.out.println("Lỗi rồi");
                    }
                    Parent edit = FXMLLoader.load(getClass().getResource("../orderticket/edit/edit.fxml"));
                    Main.movieStage.setTitle("Edit "+this.getId());
                    Main.movieStage.setScene(new Scene(edit,Main.width,Main.height));

                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });

    }

    public Integer getId() {
        return id;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public Button getDelete() {
        return delete;
    }

    public Button getDeleteOutOrder() {
        return deleteOutOrder;
    }

    @Override
    public String toString() {
        return "Id Ticket-" +id.toString()+"/Price-"+price.toString()+"/Seat-"+getNameSeat()+"/Room-"+getNameRoom()+
                "/Start-"+getStartShow()+"/End-"+getEndShow()+"\n";

    }
}
