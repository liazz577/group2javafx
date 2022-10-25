package group2JP2.entities;

import group2JP2.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.sql.Date;
import java.time.LocalDateTime;

public class ShowTime {
    public Integer id;
    public LocalDateTime startShow;
    public LocalDateTime endShow;
    public Button choose;
    public static ShowTime selectShowTime;

    public ShowTime() {
    }

    public ShowTime(Integer id, LocalDateTime startShow, LocalDateTime endShow) {
        this.id = id;
        this.startShow = startShow;
        this.endShow = endShow;
        this.choose = new Button("List Ticket");
        this.choose.setOnAction(event -> {
            try{
                selectShowTime = this;
                Parent listMovieTicket = FXMLLoader.load(getClass().getResource("../movieticket/list/list.fxml"));
                Main.movieStage.setTitle("Movie Ticket");
                Main.movieStage.setScene(new Scene(listMovieTicket,Main.width,Main.height));
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

    public LocalDateTime getStartShow() {
        return startShow;
    }

    public void setStartShow(LocalDateTime startShow) {
        this.startShow = startShow;
    }

    public LocalDateTime getEndShow() {
        return endShow;
    }

    public void setEndShow(LocalDateTime endShow) {
        this.endShow = endShow;
    }

    public Button getChoose() {
        return choose;
    }


}
