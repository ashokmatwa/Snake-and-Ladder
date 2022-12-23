package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pair<Integer, Integer>> positionCoordinates;

    public Board() {
        this.positionCoordinates = positionCoordinates; // whwenever object created it get called
    }

    private void populatePositionCoordinates(){
        positionCoordinates = new ArrayList<>(); // new object;
        positionCoordinates.add(new Pair<Integer,Integer>(0, 0));  //for 1st index

        int x=1, y=10, xPos, yPos;
        for(int i=0;i<SnakeLadder.height;i++){
            x = 1;
            for(int j=0;j<SnakeLadder.width;j++){
                if(y%2==0){
                    xPos = x*SnakeLadder.tileSize - SnakeLadder.tileSize/2;
                }
                else{
                    xPos = SnakeLadder.width*SnakeLadder.tileSize - (x*SnakeLadder.tileSize - SnakeLadder.tileSize/2);
                }

                yPos = y*SnakeLadder.tileSize - SnakeLadder.tileSize/2;
                positionCoordinates.add(new Pair<Integer, Integer>(xPos, yPos));
                x++;
            }
            y--;
        }
    }

    public int getXCoordiantes (int position){
        return positionCoordinates.get(position).getValue();
    }

    public int getYCoordiantes (int position){
        return positionCoordinates.get(position).getValue();
    }

    //to check coordiantes
//    public static void main(String [] args){
//        Board board = new Board();
//        board.populatePositionCoordinates();
//
//        for(int i=0; i< board.positionCoordinates.size(); i++){
//            System.out.println(i+" # x:"+ board.positionCoordinates.get(i).getKey()+ " y:"+
//                    board.positionCoordinates.get(i).getKey());
//        }
//    }
}
