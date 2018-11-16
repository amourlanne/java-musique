package controller.core;

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
import javafx.stage.Window;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class ArtisteListController
 * Created by Alexis on 13/11/2018
 */
public class ArtisteListController implements Initializable {

    @FXML
    private TableView<Artiste> tableArtistesFXML;

    @FXML
    private TableColumn columNameFXML;

    @FXML
    private TableColumn columnNbAlbumFXML;

    @FXML
    private TextField textFieldSearchFXML;

    @FXML
    void onMouseClickedReturn(MouseEvent event) {
        Fenetre fenetre = new Fenetre(((Node) event.getSource()).getScene().getWindow(), "/templates/home.fxml","Musique");
        fenetre.show();
    }

    @FXML
    void onMouseClickedRow(MouseEvent event) {
        if( event.getClickCount() == 2 ) {

            Artiste artiste = tableArtistesFXML.getSelectionModel().getSelectedItem();

            Window window = ((Node) event.getSource()).getScene().getWindow();
            Fenetre fenetre = new Fenetre(window, "/templates/core/album/list.fxml","Musique - " + artiste.toString());

            AlbumListController controller = fenetre.getController();
            controller.setArtiste(artiste);
            controller.init();

            fenetre.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.columNameFXML.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Artiste, String>, ObservableValue<String>>) param -> {

            final Artiste artiste = param.getValue();
            return new SimpleStringProperty(artiste.toString());
        });

        this.columnNbAlbumFXML.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Artiste, String>, ObservableValue<String>>) param -> {

            final Artiste artiste = param.getValue();
            return new SimpleStringProperty(Integer.toString(artiste.getAlbums().size()));
        });

        FilteredList<Artiste> filteredData = new FilteredList<>(FXCollections.observableArrayList(Discotheque.getArtistes()), p -> true);

        this.textFieldSearchFXML.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(artiste -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (artiste.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Artiste> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(this.tableArtistesFXML.comparatorProperty());

        this.tableArtistesFXML.setItems(sortedData);

    }
}
