package group2JP2.orderticket.edit;

import group2JP2.Main;
import group2JP2.dao.impls.MovieTicketRepository;
import group2JP2.entities.MovieTicket;
import group2JP2.entities.OrderTicket;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class EditOrderController implements Initializable {

    public TableView<MovieTicket> tbMovieTicket;
    public TableColumn<MovieTicket,Integer> tdIdTicket;
    public TableColumn<MovieTicket,String> tdNameFilm;
    public TableColumn<MovieTicket, LocalDateTime> tdStarShow;
    public TableColumn<MovieTicket,LocalDateTime> tdEndShow;
    public TableColumn<MovieTicket,String> tdSeatName;
    public TableColumn<MovieTicket,String> tdRoomName;
    public TableColumn<MovieTicket,Integer> tdPrice;
    public TableColumn<MovieTicket, Button> tdAction;

    public TextField txtTotalMoney;
    public TextField txtQtyTicket;

    public void goToHome(ActionEvent actionEvent) throws Exception {
        Parent home = FXMLLoader.load(getClass().getResource("../../home.fxml"));
        Main.movieStage.setTitle("HOME");
        Main.movieStage.setScene(new Scene(home,Main.width,Main.height));

    }
    public void goToListOrder(ActionEvent actionEvent) throws Exception {
        Parent listOrder = FXMLLoader.load(getClass().getResource("../list/list.fxml"));
        Main.movieStage.setTitle("List Order");
        Main.movieStage.setScene(new Scene(listOrder,Main.width,Main.height));
    }

    public void editOrder(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tdIdTicket.setCellValueFactory(new PropertyValueFactory<MovieTicket,Integer>("id"));
        tdNameFilm.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameFilm"));
        tdStarShow.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("startShow"));
        tdEndShow.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("endShow"));
        tdSeatName.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameSeat"));
        tdRoomName.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameRoom"));
        tdPrice.setCellValueFactory(new PropertyValueFactory<MovieTicket,Integer>("price"));
//        tdAction.setCellValueFactory(new PropertyValueFactory<MovieTicket,Button>("deleteOutOrder"));
        ObservableList<MovieTicket> ls = FXCollections.observableArrayList();
        MovieTicketRepository mtr = new MovieTicketRepository();
        ls.addAll(mtr.findFilmOrder(OrderTicket.editedOrder));
        tbMovieTicket.setItems(ls);
        txtQtyTicket.setText(String.valueOf(ls.size()));
        Integer t = new Integer(0);
        for(MovieTicket s:ls){
            t+=s.getPrice();
        }
        txtTotalMoney.setText(t.toString());
    }
}
