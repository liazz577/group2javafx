package group2JP2;

import group2JP2.dao.impls.FilmAndTypeRepository;
import group2JP2.dao.impls.FilmRepository;
import group2JP2.entities.Film;
import group2JP2.entities.FilmAndType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    }
}
