package group2JP2.entities;

import group2JP2.Main;
import group2JP2.dao.impls.FilmRepository;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.ArrayList;


public class Film {
    public Integer id;
    public String name;
    public String author;
    public Integer time;
    public String content;
    public Button choose;
    public String nameType;
    public static Film selectFilm;
    public static ArrayList<ShowTime> filmShow;

    public Film() {
    }

    public Film(Integer id, String name, String author, Integer time, String content) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.time = time;
        this.content = content;
        this.choose = new Button("Choose");
        this.choose.setOnAction(event -> {
            try{
                selectFilm = this;
                filmShow = this.showFilm();
                Parent listShow = FXMLLoader.load(getClass().getResource("../showtime/list/list.fxml"));
                Main.movieStage.setTitle("Show Time of"+this.getName());
                Main.movieStage.setScene(new Scene(listShow,Main.width,Main.height));
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Button getChoose() {
        return choose;
    }

    public void setChoose(Button choose) {
        this.choose = choose;
    }

    public ArrayList<TypeOfFilm> type(){
        FilmRepository fr = new FilmRepository();
        return fr.findType(this);
    }
    public String getNameType(){
        return this.type().toString();
    }

    public ArrayList<ShowTime> showFilm(){
        FilmRepository fr = new FilmRepository();
        return fr.findShow(this);
    }


}
