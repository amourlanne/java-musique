package controller;

import entity.tools.Fenetre;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class HomeController
 * Created by Alexis on 16/02/2018
 */
public class HomeController implements Initializable {

    @FXML
    void onMouseClickedListAlbums(MouseEvent event) {
        Window window = ((Node) event.getSource()).getScene().getWindow();
        Fenetre fenetre = new Fenetre(window, "/templates/core/album/list.fxml","Musique - Albums");
        fenetre.show();
    }

    @FXML
    void onMouseClickedListArtists(MouseEvent event) {
        Window window = ((Node) event.getSource()).getScene().getWindow();
        Fenetre fenetre = new Fenetre(window, "/templates/core/artiste/list.fxml","Musique - Artistes");
        fenetre.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
