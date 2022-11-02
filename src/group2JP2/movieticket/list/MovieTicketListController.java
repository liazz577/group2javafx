package group2JP2.movieticket.list;

import group2JP2.Main;
import group2JP2.dao.impls.MovieTicketRepository;
import group2JP2.entities.Film;
import group2JP2.entities.MovieTicket;
import group2JP2.entities.ShowTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MovieTicketListController implements Initializable {
    public TableView<MovieTicket> tbMovieTicket;
    public TableColumn<MovieTicket,Integer> tdIdTicket;
    public TableColumn<MovieTicket,String> tdNameFilm;
    public TableColumn<MovieTicket, LocalDateTime> tdStarShow;
    public TableColumn<MovieTicket,LocalDateTime> tdEndShow;
    public TableColumn<MovieTicket,String> tdSeatName;
    public TableColumn<MovieTicket,String> tdRoomName;
    public TableColumn<MovieTicket,Float> tdPrice;
    public TableColumn<MovieTicket, Button> tdAction;

    public void goToListShow(ActionEvent actionEvent) throws Exception {
        Parent listShow = FXMLLoader.load(getClass().getResource("../../showtime/list/list.fxml"));
        Main.movieStage.setTitle("List Show");
        Main.movieStage.setScene(new Scene(listShow,Main.width,Main.height));
    }

    public void goToListFilm(ActionEvent actionEvent) throws Exception {
        Parent home = FXMLLoader.load(getClass().getResource("../../home.fxml"));
        Main.movieStage.setTitle("HOME");
        Main.movieStage.setScene(new Scene(home,Main.width,Main.height));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Film.selectFilm!=null&& ShowTime.selectShowTime!=null){
            tdIdTicket.setCellValueFactory(new PropertyValueFactory<MovieTicket,Integer>("id"));
            tdNameFilm.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameFilm"));
            tdStarShow.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("startShow"));
            tdEndShow.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("endShow"));
            tdSeatName.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameSeat"));
            tdRoomName.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameRoom"));
            tdPrice.setCellValueFactory(new PropertyValueFactory<MovieTicket,Float>("price"));
            tdAction.setCellValueFactory(new PropertyValueFactory<MovieTicket,Button>("choose"));
            ObservableList<MovieTicket> ls = FXCollections.observableArrayList();
            MovieTicketRepository mtr = new MovieTicketRepository();
            ls.addAll(mtr.findFilmShow(Film.selectFilm,ShowTime.selectShowTime));
            ObservableList<MovieTicket> li = FXCollections.observableArrayList();
            for(MovieTicket s:ls){
                int k=0;
                for(int i=0;i<MovieTicket.selectMovieTicket.size();i++){
                   if(s.getId()==MovieTicket.selectMovieTicket.get(i).getId()){
                       k++;
                   }

                }
                if(k==0){
                    li.add(s);
                }
            }
            tbMovieTicket.setItems(li);
        }
    }

    public void goToCreatOrder(ActionEvent actionEvent) throws Exception {
        Parent creatOrder = FXMLLoader.load(getClass().getResource("../../orderticket/create/create.fxml"));
        Main.movieStage.setTitle("Create Order");
        Main.movieStage.setScene(new Scene(creatOrder,Main.width,Main.height));
    }

    public void goToListOrder(ActionEvent actionEvent) throws Exception {
        Parent listOrder = FXMLLoader.load(getClass().getResource("../../orderticket/list/list.fxml"));
        Main.movieStage.setTitle("List Order");
        Main.movieStage.setScene(new Scene(listOrder,Main.width,Main.height));
    }
}
