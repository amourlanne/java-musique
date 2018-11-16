package entity.tools;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Class Fenetre
 * Created by Alexis on 12/11/2018
 */
public class Fenetre {

    private Stage stage;
    private Object controller;

    public Fenetre(Window window , String path, String title) {
        this(window,path);
        this.setTitle(title);
    }

    public Fenetre(Window window , String path) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.controller = loader.getController();

        this.stage = (Stage) window;
        stage.setScene(new Scene(root));
    }

    public void setTitle( String title ) {
        if ( null != this.stage) {
            this.stage.setTitle(title);
        }
    }
    public <T> T getController() {
        return (T)this.controller;
    }

    public void show() {
        this.stage.show();
    }

}
