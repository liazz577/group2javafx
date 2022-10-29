package group2JP2.orderticket.create;


import group2JP2.Main;
import group2JP2.dao.impls.OrderTicketRepository;
import group2JP2.entities.MovieTicket;
import group2JP2.entities.OrderTicket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CreateOrderController implements Initializable {
    public TableView<MovieTicket> tbMovieTicket;
    public TableColumn<MovieTicket,Integer> tdIdTicket;
    public TableColumn<MovieTicket,String> tdNameFilm;
    public TableColumn<MovieTicket, LocalDateTime> tdStarShow;
    public TableColumn<MovieTicket,LocalDateTime> tdEndShow;
    public TableColumn<MovieTicket,String> tdSeatName;
    public TableColumn<MovieTicket,String> tdRoomName;
    public TableColumn<MovieTicket,Float> tdPrice;

    public TextField txtTotalMoney;
    public TextField txtQtyTicket;

    public void goToMovieTicket(ActionEvent actionEvent) throws Exception {
        Parent listMovieTicket = FXMLLoader.load(getClass().getResource("../../movieticket/list/list.fxml"));
        Main.movieStage.setTitle("List Movie Ticket");
        Main.movieStage.setScene(new Scene(listMovieTicket,Main.width,Main.height));
    }

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

    public void createOrder(ActionEvent actionEvent){
        try{
            Float total = Float.parseFloat(txtTotalMoney.getText());
            Integer qty = Integer.parseInt(txtQtyTicket.getText());
            OrderTicket o = new OrderTicket(null,qty,total);
            OrderTicketRepository otr = new OrderTicketRepository();
            if(otr.update(o)){
                goToListOrder(null);
            }else{
                System.out.println("Error");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(MovieTicket.selectMovieTicket!=null){
            tdIdTicket.setCellValueFactory(new PropertyValueFactory<MovieTicket,Integer>("id"));
            tdNameFilm.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameFilm"));
            tdStarShow.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("startShow"));
            tdEndShow.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("endShow"));
            tdSeatName.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameSeat"));
            tdRoomName.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameRoom"));
            tdPrice.setCellValueFactory(new PropertyValueFactory<MovieTicket,Float>("price"));
            ObservableList<MovieTicket> ls = FXCollections.observableArrayList();
            ls.addAll(MovieTicket.selectMovieTicket);
            tbMovieTicket.setItems(ls);
            txtQtyTicket.setText(String.valueOf(MovieTicket.selectMovieTicket.size()));
            Float t = new Float(0);
            for(MovieTicket s:MovieTicket.selectMovieTicket){
                t+=s.getPrice();
            }
            txtTotalMoney.setText(t.toString());
        }
    }
}

