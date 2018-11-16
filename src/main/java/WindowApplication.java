import entity.core.Discotheque;
import exception.ErreurFormatFichierException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class WindowApplication
 * Created by Alexis on 31/10/2018
 */
public class WindowApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("templates/home.fxml"));
        stage.setTitle("Musique");
        stage.setScene(new Scene(root));
        stage.setX(780);
        stage.setY(50);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            Discotheque.charger();
            launch(args);
        } catch (ErreurFormatFichierException e) {
            System.err.println(e.getMessage());
        }
    }
}
