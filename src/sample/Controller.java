package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Controller {
    @FXML
    Button playSong;
    @FXML
    Button stopSong;
    @FXML
    Button pauseSong;
    @FXML
    Button resumeSong;
    @FXML
    TextField songID;

    Media song;
    MediaPlayer songPlayer;

    public void playSongById(ActionEvent actionEvent) throws URISyntaxException {
        String id = songID.getText ();
        song = new Media(new URI("https://www.newgrounds.com/audio/download/"+id).toString ());
        songPlayer = new MediaPlayer ( song );
        songPlayer.play ();
        songPlayer.onEndOfMediaProperty ().setValue ( this::stopSong );
        playSong.setDisable ( true );
        pauseSong.setDisable ( false );
        stopSong.setDisable ( false );
    }

    private void stopSong() {
        stopSong(new ActionEvent());
    }

    public void stopSong(ActionEvent actionEvent) {
        songPlayer.stop ();
        stopSong.setDisable ( true );
        playSong.setDisable ( false );
        pauseSong.setDisable ( true );
        resumeSong.setDisable ( true );
    }

    public void pauseSong(ActionEvent actionEvent) {
        songPlayer.pause ();
        pauseSong.setDisable ( true );
        resumeSong.setDisable ( false );
    }

    public void resumeSong(ActionEvent actionEvent) {
        songPlayer.play ();
        pauseSong.setDisable ( false );
        resumeSong.setDisable ( true );
    }

    public void openNewgroundsAudio(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://www.newgrounds.com/audio/"));
        } catch (IOException e) {
            e.printStackTrace ( );
        } catch (URISyntaxException e) {
            e.printStackTrace ( );
        }
    }
}
