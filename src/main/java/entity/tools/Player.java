package entity.tools;

import javazoom.jl.decoder.JavaLayerException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class Player
 * Created by Alexis on 13/11/2018
 */
public class Player extends Thread {

    private String filePath;

    public Player(String filePath) {
        this.filePath = filePath;
    }

    // Runnable members

    public void run() {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.filePath);
            javazoom.jl.player.Player sound = new javazoom.jl.player.Player(fileInputStream);
            sound.play();

        }catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
        }catch(JavaLayerException e) {
            e.printStackTrace();
        }

    }

}
