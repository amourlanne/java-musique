package controller.core;

import entity.core.Album;
import entity.core.Artiste;
import entity.core.Piste;
import entity.tools.Fenetre;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.util.Callback;
import entity.tools.Utils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class HomeController
 * Created by Alexis on 16/02/2018
 */
public class AlbumShowController implements Initializable {

    @FXML
    private TableView<Piste> tablePistesFXML;

    @FXML
    private TableColumn columPisteFXML;

    @FXML
    private TableColumn columDateFXML;

    @FXML
    private Text titreFXML;

    @FXML
    private Text dureeFXML;

    @FXML
    private Text artisteFXML;

    private Boolean isFromArtiste = false;

    private Album album;

    private Piste currentPiste;

    @FXML
    void onMouseClickedHome(MouseEvent event) {
        Window window = ((Node) event.getSource()).getScene().getWindow();
        Fenetre fenetre = new Fenetre(window, "/templates/home.fxml","Musique");
        fenetre.show();
    }

    @FXML
    void onMouseClickedReturn(MouseEvent event) {

        if (this.currentPiste != null) this.currentPiste.stop();

        if(this.isFromArtiste) {
            Artiste artiste = this.album.getArtiste();
            Window window = ((Node) event.getSource()).getScene().getWindow();
            Fenetre fenetre = new Fenetre(window, "/templates/core/album/list.fxml","Musique - " + artiste.toString());

            AlbumListController controller = fenetre.getController();
            controller.setArtiste(artiste);
            controller.init();

            fenetre.show();
        } else {
            Window window = ((Node) event.getSource()).getScene().getWindow();
            Fenetre fenetre = new Fenetre(window, "/templates/core/album/list.fxml","Musique - Albums");
            fenetre.show();
        }
    }

    @FXML
    void onMouseClickedRow(MouseEvent event) {
        if( event.getClickCount() == 2 ) {

            if (this.currentPiste != null) this.currentPiste.stop();

            this.currentPiste = tablePistesFXML.getSelectionModel().getSelectedItem();
            this.currentPiste.play();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.columPisteFXML.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Piste, String>, ObservableValue<String>>) param -> {

            final Piste piste = param.getValue();
            return new SimpleStringProperty(piste.toString());
        });

        this.columDateFXML.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Piste, String>, ObservableValue<String>>) param -> {
            final Piste piste = param.getValue();
            return new SimpleStringProperty(Utils.showTime(piste.getDuree()));
        });

    }

    public void setAlbum(Album album ) {
        this.album = album;
    }
    public void init () {
        if (null != this.album) {

            this.titreFXML.setText(this.album.toString());
            this.artisteFXML.setText(this.album.getArtiste().toString());
            this.dureeFXML.setText(Utils.showTime(this.album.getDuree()));

            this.tablePistesFXML.getItems().clear();
            this.tablePistesFXML.getItems().addAll(this.album.getPistes());
        }
    }

    public Boolean getFromArtiste() {
        return isFromArtiste;
    }

    public void setFromArtiste(Boolean fromArtiste) {
        isFromArtiste = fromArtiste;
    }
}
