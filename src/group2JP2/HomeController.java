package group2JP2;

import group2JP2.dao.impls.FilmAndTypeRepository;
import group2JP2.dao.impls.FilmRepository;
import group2JP2.entities.Film;
import group2JP2.entities.FilmAndType;
import group2JP2.entities.ShowTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public TableView<Film> tbFilm;
    public TableColumn<Film,Integer> tdId;
    public TableColumn<Film,String> tdName;
    public TableColumn<Film,String> tdAuthor;
    public TableColumn<Film,Integer> tdTime;
    public TableColumn<Film,String> tdContent;
    public TableColumn<Film,String> tdType;
    public TableColumn<Film, Button> tdAction;

    public ComboBox<ShowTime> cbListShow;
    public Text txtNameFilm;
    public static ShowTime selectedShow;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tdId.setCellValueFactory(new PropertyValueFactory<Film,Integer>("id"));
        tdName.setCellValueFactory(new PropertyValueFactory<Film,String>("name"));
        tdAuthor.setCellValueFactory(new PropertyValueFactory<Film,String>("author"));
        tdTime.setCellValueFactory(new PropertyValueFactory<Film,Integer>("time"));
        tdContent.setCellValueFactory(new PropertyValueFactory<Film,String>("content"));
        tdType.setCellValueFactory(new PropertyValueFactory<Film,String>("nameType"));
        tdAction.setCellValueFactory(new PropertyValueFactory<Film,Button>("choose"));
        ObservableList<Film> ls = FXCollections.observableArrayList();
        FilmRepository fr = new FilmRepository();
        ls.addAll(fr.all());
        tbFilm.setItems(ls);
        ObservableList<FilmAndType> lft = FXCollections.observableArrayList();
        FilmAndTypeRepository fatr = new FilmAndTypeRepository();
        lft.addAll(fatr.all());

        if(Film.selectFilm!=null){
            txtNameFilm.setText(Film.selectFilm.getName());
            ObservableList<ShowTime> ss = FXCollections.observableArrayList();
            ss.addAll(Film.filmShow);
            cbListShow.setItems(ss);
        }

    }

    public void goToListTicket(ActionEvent actionEvent) throws Exception {
        selectedShow = cbListShow.getSelectionModel().getSelectedItem();
        Parent listTicket = FXMLLoader.load(getClass().getResource("movieticket/list/list.fxml"));
        Main.movieStage.setTitle("List Movie Ticket");
        Main.movieStage.setScene(new Scene(listTicket,Main.width,Main.height));
    }
}
