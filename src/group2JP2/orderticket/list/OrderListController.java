package group2JP2.orderticket.list;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderListController implements Initializable {

    public TableView<OrderTicket> tbOrder;
    public TableColumn<OrderTicket,Integer> tdOrderId;
    public TableColumn<OrderTicket,Integer> tdQtyTicket;
    public TableColumn<OrderTicket,Integer> tdTotalMoney;
    public TableColumn<OrderTicket, Button> tdAction;
    public TableColumn<OrderTicket,String> tdMovieTicket;
    public TableColumn<OrderTicket,String> tdCreateDate;

    public void goToHome(ActionEvent actionEvent) throws Exception {
        Parent home = FXMLLoader.load(getClass().getResource("../../home.fxml"));
        Main.movieStage.setTitle("HOME");
        Main.movieStage.setScene(new Scene(home,Main.width,Main.height));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tdOrderId.setCellValueFactory(new PropertyValueFactory<OrderTicket,Integer>("id"));
        tdQtyTicket.setCellValueFactory(new PropertyValueFactory<OrderTicket,Integer>("qty"));
        tdTotalMoney.setCellValueFactory(new PropertyValueFactory<OrderTicket,Integer>("totalMoney"));
        tdMovieTicket.setCellValueFactory(new PropertyValueFactory<OrderTicket,String>("nameMovieTicket"));
        tdCreateDate.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));
        tdAction.setCellValueFactory(new PropertyValueFactory<OrderTicket,Button>("edit"));
        ObservableList<OrderTicket> ls = FXCollections.observableArrayList();
        OrderTicketRepository otr = new OrderTicketRepository();
//        ls.addAll(otr.all());
        ArrayList<OrderTicket> ooo = new ArrayList<>();
        for(OrderTicket s: otr.all()){
            if(s.getId()!=0&&s.getQty()!=0){
                ooo.add(s);
            }
        }
        ls.addAll(ooo);

        tbOrder.setItems(ls);

    }
}
