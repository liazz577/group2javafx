package group2JP2.orderticket.create;


import group2JP2.Main;
import group2JP2.dao.impls.MovieTicketRepository;
import group2JP2.dao.impls.OrderTicketRepository;
import group2JP2.entities.MovieTicket;
import group2JP2.entities.OrderTicket;
import group2JP2.enums.RepoType;
import group2JP2.factory.RepositoryFactory;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateOrderController implements Initializable {
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
            OrderTicketRepository otr = new OrderTicketRepository();
            ArrayList<OrderTicket> ls = new ArrayList<>();
            ls.addAll(otr.all());
             Integer t=Integer.valueOf(0);
            for(int i=1;i<ls.size();i++){
                if(ls.get(i).getId()>=ls.get(i-1).getId()){
                    t = ls.get(i).getId();
                }
            }
            Integer total = Integer.parseInt(txtTotalMoney.getText());
            Integer qty = Integer.parseInt(txtQtyTicket.getText());

            OrderTicket o = new OrderTicket(Integer.valueOf(t+1),qty,total);


            if(otr.create(o)){
                System.out.println("Success");
            }else {
                System.out.println("Error");
                return;
            }
            for(MovieTicket s:MovieTicket.selectMovieTicket){
                s.setOrderId(o.getId());
                MovieTicketRepository mtr = new MovieTicketRepository();
                if(mtr.update(s)){
                    System.out.println("Done");
                }else{
                    System.out.println("Update oId for MovieTicket Error");
                    return;
                }
            }
            MovieTicket.selectMovieTicket.removeAll(MovieTicket.selectMovieTicket);
            goToListOrder(null);

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
            tdPrice.setCellValueFactory(new PropertyValueFactory<MovieTicket,Integer>("price"));
            tdAction.setCellValueFactory(new PropertyValueFactory<MovieTicket,Button>("delete"));
            ObservableList<MovieTicket> ls = FXCollections.observableArrayList();
            ls.addAll(MovieTicket.selectMovieTicket);
            tbMovieTicket.setItems(ls);
            txtQtyTicket.setText(String.valueOf(MovieTicket.selectMovieTicket.size()));
            Integer t = new Integer(0);
            for(MovieTicket s:MovieTicket.selectMovieTicket){
                t+=s.getPrice();
            }
            txtTotalMoney.setText(t.toString());


        }
    }

}
