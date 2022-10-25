package group2JP2.orderticket.list;

import group2JP2.Main;
import group2JP2.dao.impls.OrderTicketRepository;
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
import java.util.ResourceBundle;

public class OrderListController implements Initializable {

    public TableView<OrderTicket> tbOrder;
    public TableColumn<OrderTicket,Integer> tdOrderId;
    public TableColumn<OrderTicket,Integer> tdQtyTicket;
    public TableColumn<OrderTicket,Float> tdTotalMoney;
    public TableColumn<OrderTicket, Button> tdAction;

    public void goToHome(ActionEvent actionEvent) throws Exception {
        Parent home = FXMLLoader.load(getClass().getResource("../../home.fxml"));
        Main.movieStage.setTitle("HOME");
        Main.movieStage.setScene(new Scene(home,Main.width,Main.height));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tdOrderId.setCellValueFactory(new PropertyValueFactory<OrderTicket,Integer>("id"));
        tdQtyTicket.setCellValueFactory(new PropertyValueFactory<OrderTicket,Integer>("qty"));
        tdTotalMoney.setCellValueFactory(new PropertyValueFactory<OrderTicket,Float>("totalMoney"));
        tdAction.setCellValueFactory(new PropertyValueFactory<OrderTicket,Button>("choose"));
        ObservableList<OrderTicket> ls = FXCollections.observableArrayList();
        OrderTicketRepository otr = new OrderTicketRepository();
        ls.addAll(otr.all());
        tbOrder.setItems(ls);

    }
}