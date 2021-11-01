package home.battleShips;

import home.battleShips.field.FieldPicture;
import home.battleShips.field.Skin;
import home.battleShips.model.Game;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Controller {


    @FXML
    BorderPane playerPane;
    @FXML
    BorderPane computerPane;
    @FXML
    Button newGame;


    private Game game ;

    @FXML
    private void initialize(){
        newGame();
//        showVictory();
//        newGame.setId("sea");



    }


    public void newGame() {
        game = new Game(this);

        playerPane.setCenter(    game.getPlayerField());
        computerPane.setCenter(  game.getCpuField()   );

        playerPane.setBottom(null);
        computerPane.setBottom(null);

//        setCSS(Skin.yuraStyle);
        setRandomCSS();

    }



    public void showVictory() {
        MediaView mediaView = getMediaView(FieldPicture.VICTORY);
        playerPane.setCenter( mediaView);
        playerPane.setBottom(new Label("Победа"));
    }

    public void showDefeat(){
        MediaView mediaView = getMediaView(FieldPicture.LOSS);
        computerPane.setCenter(mediaView);
        computerPane.setBottom(new Label("Поражение"));
    }

    private MediaView getMediaView(FieldPicture fieldPicture) {
        URL url = fieldPicture.getImagePath();
        Media media = new Media( url.toString() );

        MediaPlayer player = new MediaPlayer(media);
        //player.setStartTime( Duration.INDEFINITE);
        player.setAutoPlay(true);
        player.setCycleCount( MediaPlayer.INDEFINITE );

        MediaView mediaView = new MediaView(player);
        mediaView.setFitWidth(350);

//        StaticUtils.pause(2000);

        return mediaView;
    }

    private void setCSS(Skin skin){

        Parent parent = newGame.getParent();
        parent.getStylesheets().clear();
        parent.getStylesheets().add(skin.getFileName().toString());
        System.out.println( parent.getStylesheets() );
    }

    private void setRandomCSS(){

        List<Skin> skins = Arrays.asList(Skin.values());
        int number = (int) Math.round( Math.random()*(skins.size()-1));
        Skin skin = skins.get(number);

        Parent parent = newGame.getParent();

        parent.getStylesheets().clear();
        parent.getStylesheets().add(skin.getFileName().toString());
        System.out.println( parent.getStylesheets() );
    }

}
