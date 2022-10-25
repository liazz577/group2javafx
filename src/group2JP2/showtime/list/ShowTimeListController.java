package group2JP2.showtime.list;

import group2JP2.Main;
import group2JP2.entities.Film;
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

public class ShowTimeListController implements Initializable {
    public TableView<ShowTime> tbShowTime;
    public TableColumn<ShowTime,Integer> tdId;
    public TableColumn<ShowTime, LocalDateTime> tdStartShow;
    public TableColumn<ShowTime,LocalDateTime> tdEndShow;
    public TableColumn<ShowTime, Button> tdAction;

    public void goToListFilm(ActionEvent actionEvent) throws Exception {
        Parent listFilm = FXMLLoader.load(getClass().getResource("../../home.fxml"));
        Main.movieStage.setTitle("HOME");
        Main.movieStage.setScene(new Scene(listFilm,Main.width,Main.height));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Film.filmShow!=null){
            tdId.setCellValueFactory(new PropertyValueFactory<ShowTime,Integer>("id"));
            tdStartShow.setCellValueFactory(new PropertyValueFactory<ShowTime,LocalDateTime>("startShow"));
            tdEndShow.setCellValueFactory(new PropertyValueFactory<ShowTime,LocalDateTime>("endShow"));
            tdAction.setCellValueFactory(new PropertyValueFactory<ShowTime,Button>("choose"));
            ObservableList<ShowTime> ls = FXCollections.observableArrayList();
            ls.addAll(Film.filmShow);
            tbShowTime.setItems(ls);
        }
    }
}
