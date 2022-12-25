package com.example.snakeandladder;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Player {
    private Circle coin;   // circle class

    private String name;

    private int coinPosition;

    private static Board gameBoard = new Board(); // static becoz both playes playin gon same board



    public Player(int tileSize, Color coinColor, String playerName){ //constructor
        coinPosition = 1;  // game starts with one
        name = playerName;
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        coin.setTranslateX(20);   // coin size 1*40-20 =20 so put it on its centre 0+20
        coin.setTranslateY(380);  // 10*40-20 = 380 for y axis

        //x = 1 * tilesize - tilesize/2
        //y = 10 * tilesize - tilesize/2
    }

    public void movePlayer(int diceValue){  // move --> what is the value comes on dice
        if(coinPosition + diceValue <= 100){  //out of board
            coinPosition += diceValue;

//            coin.setTranslateX(gameBoard.getXCoordinates(coinPosition)); //to move coin
//            coin.setTranslateY(gameBoard.getYCoordinates(coinPosition));
            translatePlayer(); // calling for animation movement

            int newPosition = gameBoard.getNextPosition(coinPosition);
            if(newPosition != coinPosition){
                coinPosition = newPosition;
                translatePlayer();
            }
        }
    }

    //animate functionality ---> moving dice smoothly
    private void translatePlayer(){
        TranslateTransition move = new TranslateTransition(Duration.millis(1000), this.coin);
        move.setToX(gameBoard.getXCoordinates(coinPosition)); // to set coordinates
        move.setToY(gameBoard.getYCoordinates(coinPosition));

        move.setAutoReverse(false); // we do not autoreverese  --> false
        move.play();
    }


    public String playerWon(){
        if(coinPosition == 100)
            return name+" Won the Game";

        return null;
    }


    public Circle getCoin() {
        return coin;
    }

    public int getCoinPosition() {
        return coinPosition;
    }

    public String getName() {
        return name;
    }
}
