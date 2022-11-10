package group2JP2.movieticket.list;

import group2JP2.HomeController;
import group2JP2.Main;
import group2JP2.dao.impls.MovieTicketRepository;
import group2JP2.dao.impls.OrderTicketRepository;
import group2JP2.entities.Film;
import group2JP2.entities.MovieTicket;
import group2JP2.entities.OrderTicket;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieTicketListController implements Initializable {
    public TableView<MovieTicket> tbMovieTicket;
    public TableColumn<MovieTicket,Integer> tdIdTicket;
    public TableColumn<MovieTicket,String> tdNameFilm;
    public TableColumn<MovieTicket, LocalDateTime> tdStarShow;
    public TableColumn<MovieTicket,LocalDateTime> tdEndShow;
    public TableColumn<MovieTicket,String> tdSeatName;
    public TableColumn<MovieTicket,String> tdRoomName;
    public TableColumn<MovieTicket,Integer> tdPrice;
    public TableColumn<MovieTicket, Button> tdAction;
    public TableColumn<MovieTicket,String> tdTypeSeat;
    public TableView<MovieTicket> tbBag;
    public TableColumn<MovieTicket,Integer> tdIdTicketBag;
    public TableColumn<MovieTicket,String> tdNameFilmBag;
    public TableColumn<MovieTicket, LocalDateTime> tdStarShowBag;
    public TableColumn<MovieTicket,LocalDateTime> tdEndShowBag;
    public TableColumn<MovieTicket,String> tdSeatNameBag;
    public TableColumn<MovieTicket,String> tdRoomNameBag;
    public TableColumn<MovieTicket,Integer> tdPriceBag;
    public TableColumn<MovieTicket,String>  tdTypeSeatBag;
    public TableColumn<MovieTicket, Button> tdActionBag;

    public void goToListShow(ActionEvent actionEvent) throws Exception {
        Parent listShow = FXMLLoader.load(getClass().getResource("../../home.fxml"));
        Main.movieStage.setTitle("Home");
        Main.movieStage.setScene(new Scene(listShow,Main.width,Main.height));
    }

    public void goToListFilm(ActionEvent actionEvent) throws Exception {
        Parent home = FXMLLoader.load(getClass().getResource("../../home.fxml"));
        Main.movieStage.setTitle("HOME");
        Main.movieStage.setScene(new Scene(home,Main.width,Main.height));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Film.selectFilm!=null&& HomeController.selectedShow !=null){
            tdIdTicket.setCellValueFactory(new PropertyValueFactory<MovieTicket,Integer>("id"));
            tdNameFilm.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameFilm"));
            tdStarShow.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("startShow"));
            tdEndShow.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("endShow"));
            tdSeatName.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameSeat"));
            tdRoomName.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameRoom"));
            tdPrice.setCellValueFactory(new PropertyValueFactory<MovieTicket,Integer>("price"));
            tdTypeSeat.setCellValueFactory(new PropertyValueFactory<>("typeSeat"));
            tdAction.setCellValueFactory(new PropertyValueFactory<MovieTicket,Button>("choose"));
            ObservableList<MovieTicket> ls = FXCollections.observableArrayList();
            MovieTicketRepository mtr = new MovieTicketRepository();
            ls.addAll(mtr.findFilmShow(Film.selectFilm,HomeController.selectedShow));
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
        if(MovieTicket.selectMovieTicket!=null){
            tdIdTicketBag.setCellValueFactory(new PropertyValueFactory<MovieTicket,Integer>("id"));
            tdNameFilmBag.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameFilm"));
            tdStarShowBag.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("startShow"));
            tdEndShowBag.setCellValueFactory(new PropertyValueFactory<MovieTicket,LocalDateTime>("endShow"));
            tdSeatNameBag.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameSeat"));
            tdRoomNameBag.setCellValueFactory(new PropertyValueFactory<MovieTicket,String>("nameRoom"));
            tdPriceBag.setCellValueFactory(new PropertyValueFactory<MovieTicket,Integer>("price"));
            tdTypeSeatBag.setCellValueFactory(new PropertyValueFactory<>("typeSeat"));
            tdActionBag.setCellValueFactory(new PropertyValueFactory<MovieTicket,Button>("delete"));
            ObservableList<MovieTicket> lm = FXCollections.observableArrayList();
            lm.addAll(MovieTicket.selectMovieTicket);
            tbBag.setItems(lm);

        }
    }

    public void goToListOrder(ActionEvent actionEvent) throws Exception {
        Parent listOrder = FXMLLoader.load(getClass().getResource("../../orderticket/list/list.fxml"));
        Main.movieStage.setTitle("List Order");
        Main.movieStage.setScene(new Scene(listOrder,Main.width,Main.height));
    }

    public void createOrder(ActionEvent actionEvent)  {
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
            Integer qty = Integer.valueOf(MovieTicket.selectMovieTicket.size());
            Integer total = Integer.valueOf(0);
            for(MovieTicket s:MovieTicket.selectMovieTicket){
                total+=s.getPrice();
            }
            String s1 = LocalDateTime.now().toString().substring(0,11);
            String s2 = LocalDateTime.now().toString().substring(11,21);
            String s3 = s1+s2;
            OrderTicket o = new OrderTicket(Integer.valueOf(t+1),qty,total,s3);

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
}
