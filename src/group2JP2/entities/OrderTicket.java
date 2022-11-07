package group2JP2.entities;


import group2JP2.Main;
import group2JP2.dao.impls.FilmRepository;
import group2JP2.dao.impls.MovieTicketRepository;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class OrderTicket {
   public Integer id;
   public Integer qty;
   public Integer totalMoney;
   public Button edit;
   public String nameMovieTicket;
   public static OrderTicket editedOrder;


    public OrderTicket() {
    }

    public OrderTicket(Integer id, Integer qty, Integer totalMoney) {
        this.id = id;
        this.qty = qty;
        this.totalMoney = totalMoney;
        this.edit = new Button("More");
        this.edit.setOnAction(event -> {
            try{
                editedOrder = this;
                Parent edit = FXMLLoader.load(getClass().getResource("../orderticket/edit/edit.fxml"));
                Main.movieStage.setTitle("Edit "+this.getId());
                Main.movieStage.setScene(new Scene(edit,Main.width,Main.height));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }



        });
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Button getEdit() {
        return edit;
    }

    public ArrayList<MovieTicket> findMovieTicket(){
        MovieTicketRepository mtr = new MovieTicketRepository();
        return mtr.findFilmOrder(this);
    }


    public String getNameMovieTicket() {
        return findMovieTicket().toString();
    }
}
