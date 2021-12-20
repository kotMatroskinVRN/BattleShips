package home.battleShips;

import home.battleShips.field.*;
import home.battleShips.model.Game;
import home.battleShips.model.Turn;
import home.battleShips.model.cpu.LogicFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
    private ChoiceBox<Skin> skinBox;
    @FXML
    private ChoiceBox<LogicFactory> difficultyBox;
    @FXML
    private ListView<String> playerTurns;
    @FXML
    private ListView<String> cpuTurns;
    @FXML
    private SplitPane shipsLeft;

    private  ShipBar playerShipsLeft ;
    private  ShipBar    cpuShipsLeft ;

    @FXML
    private void initialize(){
        initDifficulty();
        initSkinChoice();


    }



    @FXML
    public void newGameModal() {

        ModalNewGame modalNewGame = new ModalNewGame();
        modalNewGame.initModalNewGame(root);
        modalNewGame.getOkButton().setOnAction( (ae)->{
            newGame();
            modalNewGame.closeWindow(ae);
        });
        modalNewGame.showWindow();
    }



    public void showVictory() {

        MediaView mediaView = getMediaView(Media.VICTORY);
        playerPane.setCenter( mediaView);
        playerPane.setBottom(new Label("Победа : " + playerTurns.getItems().size()));
    }

    public void showDefeat(){
        MediaView mediaView = getMediaView(Media.LOSS);
        computerPane.setCenter(mediaView);
        computerPane.setBottom(new Label("Поражение : " + cpuTurns.getItems().size()));
    }

    public void addPlayerTurnToList(Turn turn) {
        ObservableList<String> list = playerTurns.getItems();
        list.add(turn.getCell().toString());
        playerTurns.scrollTo(list.size());
    }

    public void addCpuTurnToList(Turn turn) {
        ObservableList<String> list = cpuTurns.getItems();
        list.add(turn.getCell().toString());
        cpuTurns.scrollTo(list.size());
    }

    public ShipBar getPlayerShipsLeft() {
        return playerShipsLeft;
    }

    public ShipBar getCpuShipsLeft() {
        return cpuShipsLeft;
    }

    private void newGame() {

        Game game = new Game(this);
        game.setDifficulty(difficultyBox.getValue());

        playerPane.setCenter(    game.getPlayerField());
        computerPane.setCenter(  game.getCpuField()   );

        playerPane.setBottom(null);
        computerPane.setBottom(null);

        playerTurns.setItems( FXCollections.observableArrayList() );
        cpuTurns.setItems( FXCollections.observableArrayList() );

        initShipsLeft();


    }


    private MediaView getMediaView(Media fieldPicture) {
        URL url = fieldPicture.getMediaURL();
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
        parent.getStylesheets().add(Skin.getMainCSS().toString());
        parent.getStylesheets().add(skin.getFileName().toString());
    }

    private void setRandomCSS(){
        List<Skin> skins = Arrays.asList(Skin.values());
        int number = (int) Math.round( Math.random()*(skins.size()-1));
        Skin skin = skins.get(number);

        Parent parent = root;
        parent.getStylesheets().clear();
        parent.getStylesheets().add(Skin.getMainCSS().toString());
        parent.getStylesheets().add(skin.getFileName().toString());
    }

    private void initSkinChoice(){
        skinBox.getItems().addAll(Skin.values());
        skinBox.setValue(Skin.DEFAULT);
        skinBox.setOnAction( (ae) -> setCSS(skinBox.getValue()));
    }

    private void initDifficulty() {

        difficultyBox.getItems().addAll(LogicFactory.values());
        difficultyBox.setValue(LogicFactory.NORMAL);
        difficultyBox.setOnAction( (ae) -> newGame() );
        newGame();
    }

    private void initShipsLeft() {

        playerShipsLeft = new ShipBar(true);
           cpuShipsLeft = new ShipBar(false);

        playerShipsLeft.init();
        playerShipsLeft.setId("shipsPanePlayer");

        cpuShipsLeft.init();
        cpuShipsLeft.setId("shipsPaneCPU");

        shipsLeft.getItems().clear();
        shipsLeft.getItems().addAll(playerShipsLeft,cpuShipsLeft);
        ((BorderPane)root).setTop(shipsLeft);
    }


}
