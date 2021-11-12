package home.battleShips;

import home.battleShips.field.CSSpicture;
import home.battleShips.field.Media;
import home.battleShips.field.Skin;
import home.battleShips.model.Game;
import home.battleShips.model.cpu.LogicFactory;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class Controller {

    @FXML
    Parent root;
    @FXML
    private BorderPane playerPane;
    @FXML
    private BorderPane computerPane;
    @FXML
    private Button newGame;
    @FXML
    private ChoiceBox<Skin> skinBox;
    @FXML
    private ChoiceBox<LogicFactory> difficultyBox;


    private Game game ;

    @FXML
    private void initialize(){
        initDifficulty();
//        newGame();
//        showVictory();
//        newGame.setId("sea");
        initSkinChoise();




    }

    private void initDifficulty() {

        difficultyBox.getItems().addAll(LogicFactory.values());
        difficultyBox.setValue(LogicFactory.NORMAL);
        setDifficulty( difficultyBox.getValue() );
        difficultyBox.setOnAction( (ae) -> setDifficulty( difficultyBox.getValue() ) );
    }

    private void setDifficulty(LogicFactory value) {
        newGame();
        game.setDifficulty(value);
    }


    @FXML
    public void newGame() {
        game = new Game(this);

        playerPane.setCenter(    game.getPlayerField());
        computerPane.setCenter(  game.getCpuField()   );

        playerPane.setBottom(null);
        computerPane.setBottom(null);

//        setCSS(Skin.yuraStyle);
        //setRandomCSS();

    }



    public void showVictory() {
        MediaView mediaView = getMediaView(Media.VICTORY);
        playerPane.setCenter( mediaView);
        playerPane.setBottom(new Label("Победа"));
    }

    public void showDefeat(){
        MediaView mediaView = getMediaView(Media.LOSS);
        computerPane.setCenter(mediaView);
        computerPane.setBottom(new Label("Поражение"));
    }

    private MediaView getMediaView(Media fieldPicture) {
        URL url = fieldPicture.getImagePath();
        javafx.scene.media.Media media = new javafx.scene.media.Media( url.toString() );

        MediaPlayer player = new MediaPlayer(media);
        player.setAutoPlay(true);
        player.setCycleCount( MediaPlayer.INDEFINITE );

        MediaView mediaView = new MediaView(player);
        mediaView.setFitWidth(350);

        return mediaView;
    }

    private void setCSS(Skin skin){
        Parent parent = root;
        parent.getStylesheets().clear();
        parent.getStylesheets().add(skin.getFileName().toString());
    }

    private void setRandomCSS(){
        List<Skin> skins = Arrays.asList(Skin.values());
        int number = (int) Math.round( Math.random()*(skins.size()-1));
        Skin skin = skins.get(number);

        Parent parent = root;
        parent.getStylesheets().clear();
        parent.getStylesheets().add(skin.getFileName().toString());
    }

    private void initSkinChoise(){
        skinBox.getItems().addAll(Skin.values());
        skinBox.setValue(Skin.defaultStyle);
        skinBox.setOnAction( (ae) -> setCSS(skinBox.getValue()));
    }

}
