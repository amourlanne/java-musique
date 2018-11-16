package controller.core;

import entity.core.Album;
import entity.core.Artiste;
import entity.core.Discotheque;
import entity.tools.Fenetre;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class HomeController
 * Created by Alexis on 16/02/2018
 */
public class AlbumListController implements Initializable  {

    @FXML
    private TableView<Album> tableAlbumsFXML;

    @FXML
    private TableColumn columTitreFXML;

    @FXML
    private TableColumn columArtisteFXML;

    @FXML
    private TableColumn columNombrePistesFXML;

    @FXML
    private TableColumn columAnneeFXML;

    @FXML
    private Text artisteFXML;

    @FXML
    private TextField textFieldSearchFXML;

    @FXML
    void onMouseClickedReturn(MouseEvent event) {
        if(this.artiste != null) {
            Fenetre fenetre = new Fenetre(((Node) event.getSource()).getScene().getWindow(), "/templates/core/artiste/list.fxml","Musique - Artistes");
            fenetre.show();
        } else {
            Fenetre fenetre = new Fenetre(((Node) event.getSource()).getScene().getWindow(), "/templates/home.fxml","Musique");
            fenetre.show();
        }

    }

    private Artiste artiste;

    @FXML
    void onMouseClickedRow(MouseEvent event) {
        if( event.getClickCount() == 2 ) {

            Album album = tableAlbumsFXML.getSelectionModel().getSelectedItem();

            Window window = ((Node) event.getSource()).getScene().getWindow();
            Fenetre fenetre = new Fenetre(window, "/templates/core/album/show.fxml","Musique - " + album.toString());

            AlbumShowController controller = fenetre.getController();
            controller.setAlbum(album);
            controller.init();

            if(this.artiste != null) controller.setFromArtiste(true);

            fenetre.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.columTitreFXML.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Album, String>, ObservableValue<String>>) param -> {

            final Album album = param.getValue();
            return new SimpleStringProperty(album.getTitre());
        });

        this.columArtisteFXML.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Album, String>, ObservableValue<String>>) param -> {
            final Album album = param.getValue();
            return new SimpleStringProperty(album.getArtiste().toString());
        });

        this.columNombrePistesFXML.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Album, String>, ObservableValue<String>>) param -> {
            final Album album = param.getValue();
            return new SimpleStringProperty(Integer.toString(album.getNumberOfPiste()));
        });

        this.columAnneeFXML.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Album, String>, ObservableValue<String>>) param -> {
            final Album album = param.getValue();
            return new SimpleStringProperty(Integer.toString(album.getAnnee()));
        });

        this.init();

    }

    public void setArtiste ( Artiste artiste ) {
        this.artiste = artiste;
    }

    public void init () {
        this.artisteFXML.setText(null);

        FilteredList<Album> filteredData;

        if(this.artiste != null) {
            filteredData = new FilteredList<>(FXCollections.observableArrayList(this.artiste.getAlbums()), p -> true);
            this.artisteFXML.setText("Artiste : " + this.artiste.toString());
        } else {
            filteredData = new FilteredList<>(FXCollections.observableArrayList(Discotheque.getAlbums()), p -> true);
        }

        this.textFieldSearchFXML.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(album -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (album.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Album> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(this.tableAlbumsFXML.comparatorProperty());

        this.tableAlbumsFXML.setItems(sortedData);
    }
}
