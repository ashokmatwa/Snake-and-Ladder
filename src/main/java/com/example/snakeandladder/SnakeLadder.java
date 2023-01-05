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
    Player secondPlayer = new Player(tileSize, Color.WHITE, "Bittu"); // size decrese to avoid  the case when they are at same postion
    //tilesize-10 to show alag alag
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
//        Button playerOneButton = new Button(firstPlayer.getName());
        playerOneButton.setTranslateX(40);
        playerOneButton.setTranslateY(lowerLine+20);
        playerOneButton.setStyle("-fx-background-color: #C0C0C0;");
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(firstPlayerTurn){
                       // playerOneButton.setDisable(true);
                        firstPlayerTurn = false;
                        secondPlayerTurn = true;
                        setDiceValue();  // calling function to roll dice
                        firstPlayer.movePlayer(diceValue);
                        if(firstPlayer.playerWon() != null){
                            rolledDiceValueLabel.setText(firstPlayer.playerWon());
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Re-Start game");
//                            firstPlayer.reset();
//                            secondPlayer.reset();
                        }
//                        firstPlayerTurn = false;
//                        secondPlayerTurn = true;
                    }
                }
            }
        });


        Button playerTwoButton = new Button("Player Two");
        //Button playerOneButton = new Button(secondPlayer.getName());
        playerTwoButton.setTranslateX(290);
        playerTwoButton.setTranslateY(lowerLine+20);
        playerTwoButton.setStyle("-fx-background-color: #C0C0C0;");
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(secondPlayerTurn){
                        firstPlayerTurn = true;
                        secondPlayerTurn = false;
                        setDiceValue(); // calling function to roll dice
                        secondPlayer.movePlayer(diceValue);
                        if(secondPlayer.playerWon() != null){
                            rolledDiceValueLabel.setText(secondPlayer.playerWon());
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Re-Start game");
//                            firstPlayer.reset();
//                            secondPlayer.reset();
                        }
//                        firstPlayerTurn = true;
//                        secondPlayerTurn = false;
                    }
                }
            }
        });

        startGameButton = new Button("Start");
        startGameButton.setTranslateX(170);
        startGameButton.setTranslateY(lowerLine+50);
        startGameButton.setStyle("-fx-background-color: #C0C0C0; ");
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                startGameButton.setText("Ongoing game");
                rolledDiceValueLabel.setText("Game has started"); //added
                startGameButton.setDisable(true);
                firstPlayer.reset();
                secondPlayer.reset();
            }
        });


        rolledDiceValueLabel = new Label("Start the game");
        rolledDiceValueLabel.setTranslateY(lowerLine+20);
        rolledDiceValueLabel.setTranslateX(150);

        root.getChildren().addAll(boardImage, playerOneButton, playerTwoButton, firstPlayer.getCoin(), secondPlayer.getCoin(), rolledDiceValueLabel, startGameButton);

        return root;
    }

    private void setDiceValue(){
        diceValue = (int) (Math.random()*6+1);
        rolledDiceValueLabel.setText("Dice Value : "+ diceValue); // can add players also --> passing by parameters
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