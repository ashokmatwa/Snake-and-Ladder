package com.example.snakeandladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle coin;   // circle class

    private String name;

    private int coinPosition;

    public Player(int tileSize, Color coinColor, String playerName){
        coinPosition = 1;  // game starts with one
        name = playerName;
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        coin.setTranslateX(20);
        coin.setTranslateY(380);
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
