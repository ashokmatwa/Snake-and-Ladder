package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {


    public static  final int tileSize = 40, height = 10, width = 10;
    int lowerLine = tileSize*height;

    int diceValue;  // dice value after randomise
    Label rolledDiceValueLabel; // for labelling

    boolean firstPlayerTurn = true, secondPlayerTurn = false, gameStarted = false;
    Button startGameButton;

    Player firstPlayer = new Player(tileSize, Color.BLACK, "Ashok");
    Player secondPlayer = new Player(tileSize-10, Color.WHITE, "Bittu"); // size decrese to avoid  the case when they are at same postion
    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);

        for (int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Tile tile = new Tile(tileSize);  // calling constructor passing tileSize=40
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }
       // root.getChildren().add(new Tile(tileSize));
        Image img = new Image("C:\\Users\\ASUS\\IdeaProjects\\SnakeAndLadder\\src\\SnakeLadderBoard12Nov.jpg");
        ImageView boardImage = new ImageView(img);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);

        Button playerOneButton = new Button("Player One");
        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(lowerLine+20);
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(firstPlayerTurn){
                        setDiceValue();  // calling function to roll dice
                        firstPlayer.movePlayer(diceValue);
                        if(firstPlayer.playerWon() != null){
                            rolledDiceValueLabel.setText(firstPlayer.playerWon());
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Start game");
                        }
                        firstPlayerTurn = false;
                        secondPlayerTurn = true;
                    }
                }
            }
        });


        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setTranslateX(250);
        playerTwoButton.setTranslateY(lowerLine+20);
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(secondPlayerTurn){
                        setDiceValue(); // calling function to roll dice
                        secondPlayer.movePlayer(diceValue);
                        if(secondPlayer.playerWon() != null){
                            rolledDiceValueLabel.setText(secondPlayer.playerWon());
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Start game");
                        }
                        firstPlayerTurn = true;
                        secondPlayerTurn = false;
                    }
                }
            }
        });

        startGameButton = new Button("Start");
        startGameButton.setTranslateX(130);
        startGameButton.setTranslateY(lowerLine+50);
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                startGameButton.setText("Ongoing game");
                startGameButton.setDisable(true);
            }
        });


        rolledDiceValueLabel = new Label("Start the game");
        rolledDiceValueLabel.setTranslateY(lowerLine+20);
        rolledDiceValueLabel.setTranslateX(130);

        root.getChildren().addAll(boardImage, playerOneButton, playerTwoButton, firstPlayer.getCoin(), secondPlayer.getCoin(), rolledDiceValueLabel, startGameButton);

        return root;
    }

    private void setDiceValue(){
        diceValue = (int) (Math.random()*6+1);
        rolledDiceValueLabel.setText("Dice Value : "+ diceValue);
    }
    @Override
    public void start(Stage stage) throws IOException {
      //  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder Game !!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}