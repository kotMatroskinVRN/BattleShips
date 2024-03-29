package home.battleShips;

import home.battleShips.field.*;
import home.battleShips.field.Skin;
import home.battleShips.model.Turn;
import home.battleShips.model.cpu.LogicFactory;
import home.battleShips.utils.BattleShipsLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable , Translatable {

    @FXML
    Parent root;
    @FXML
    private BorderPane playerPane;
    @FXML
    private BorderPane computerPane;
    @FXML
    private ComboBox<Skin> skinBox;
    @FXML
    private ComboBox<LogicFactory> difficultyBox;
    @FXML
    private TurnListView playerTurns;
    @FXML
    private TurnListView cpuTurns;
    @FXML
    private SplitPane shipsLeft;
    @FXML
    private Button newGameModal;

    private  ShipBar playerShipsLeft ;
    private  ShipBar    cpuShipsLeft ;
    private ResourceBundle resourceBundle ;
    private Language language ;



    private boolean firstRun = true;

    private Skin currentSkin;
    private LogicFactory currentLogic;

    private ObservableList<Skin> skins;
    private ObservableList<LogicFactory> logics;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.resourceBundle = resourceBundle;
        language = Language.getByResourceBundle(resourceBundle);

        if(firstRun){

            currentLogic = LogicFactory.HARD;
            currentSkin  = Skin.DEFAULT;

            Translator.addSource(this);
            skins = FXCollections.observableArrayList(Skin.values());
            skinBox.setItems(skins);
            logics = FXCollections.observableArrayList(LogicFactory.values());
            difficultyBox.setItems(logics);

            playerTurns.setLanguage(language);
               cpuTurns.setLanguage(language);

        }


        initDifficulty();
        initSkinChoice();

        if(firstRun){ newGame(); }

        root.setOnKeyPressed(this::keyProcess);
        firstRun = false;
    }


    @FXML
    public void newGameModal() {

        ModalNewGame modalNewGame = new ModalNewGame();

        modalNewGame.initModalNewGame(root,language);
        modalNewGame.getOkButton().setOnAction( (ae)->{
            newGame();
            modalNewGame.closeWindow(ae);
        });
        modalNewGame.showWindow();
    }

    private void keyProcess(KeyEvent ae)  {
//        if(ae.isShiftDown() && ae.isControlDown()){
        if( ae.isControlDown()){

            if(language==Language.ENGLISH) language=Language.RUSSIAN;
            else language = Language.ENGLISH;

            playerTurns.setLanguage(language);
            cpuTurns.setLanguage(language);

            Translator.updateText(language);

        }
    }


    @Override
    public void updateText(Language language) {

        newGameModal.setText(language.getValue("button.newGame"));

        resourceBundle = language.getResourceBoundle();

        LogicFactory.updateDescription(resourceBundle);
        Skin.updateDescription(resourceBundle);

        skinBox.setOnAction(null);
        skins.setAll(Skin.values());
        skinBox.setValue(currentSkin);
        skinBox.setOnAction( (ae) -> setCSS() );

        difficultyBox.setOnAction(null);
        logics.setAll(LogicFactory.values());
        difficultyBox.setValue(currentLogic);
        difficultyBox.setOnAction( (ae) -> newGame() );

        Label label;

        label = (Label) playerPane.getBottom();
        if(label!=null) {
            label.setText(language.getValue("victory") + " : " + playerTurns.getItems().size());
        }

        label = (Label) computerPane.getBottom();
        if(label!=null) {
            label.setText(language.getValue("defeat") + " : " + playerTurns.getItems().size());
        }

    }

    public void showVictory() {

        MediaView mediaView = getMediaView(Media.VICTORY);
        playerPane.setCenter( mediaView);
        playerPane.setBottom(
                new Label(language.getValue("victory") + " : " + playerTurns.getItems().size())
        );
    }

    public void showDefeat(){
        MediaView mediaView = getMediaView(Media.LOSS);
        computerPane.setCenter(mediaView);
        computerPane.setBottom(
                new Label(language.getValue("defeat") + " : " + cpuTurns.getItems().size())
        );
    }

    public void addPlayerTurnToList(Turn turn) {
        playerTurns.addTurn(turn);
    }

    public void addCpuTurnToList(Turn turn) {
        cpuTurns.addTurn(turn);
    }

    public ShipBar getPlayerShipsLeft() {
        return playerShipsLeft;
    }

    public ShipBar getCpuShipsLeft() {
        return cpuShipsLeft;
    }


    public Language getLanguage() {
        return language;
    }

    private void newGame() {

        Game game = new Game(this);
        BattleShipsLogger.getLogger().printVerbose( difficultyBox.getValue().toString());
        currentLogic = LogicFactory.getLogic(difficultyBox.getValue().toString());
        assert currentLogic != null;
        game.setDifficulty(currentLogic);

          playerPane.setCenter(  game.getPlayerField());
        computerPane.setCenter(  game.getCpuField()   );

          playerPane.setBottom(null);
        computerPane.setBottom(null);

        playerTurns.clearTurns();
        playerTurns.updateText(language);
           cpuTurns.clearTurns();
           cpuTurns.updateText(language);

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

    private void setCSS(){
        currentSkin = Skin.getSkin(skinBox.getValue().toString());
        Parent parent = root;
        parent.getStylesheets().clear();
        parent.getStylesheets().add(Skin.getMainCSS().toString());
        parent.getStylesheets().add(currentSkin.getFileName().toString());
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

        Skin.updateDescription(resourceBundle);

        skinBox.setValue(currentSkin);
        skinBox.setOnAction( (ae) ->   setCSS() );
    }

    private void initDifficulty() {

        LogicFactory.updateDescription(resourceBundle);

        difficultyBox.setValue(currentLogic);
        difficultyBox.setOnAction( (ae) -> newGame() );

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
