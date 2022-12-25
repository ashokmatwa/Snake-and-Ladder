package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pair<Integer, Integer>> positionCoordinates;  // for coordinates of no 1 to 100
    private ArrayList<Integer> snakeLadderPosition; // for snake and ladder path transfer coordinates

    public Board() {
      //  this.positionCoordinates = positionCoordinates; // whenever object created it get called
        populatePositionCoordinates();  // to position arranged
        populateSnakeLadderPosition();
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

    private void populateSnakeLadderPosition(){
        snakeLadderPosition = new ArrayList<>();
        for(int i=0;i<101;i++){
            snakeLadderPosition.add(i);
        }

        snakeLadderPosition.set(4,25);  // index , integer  i-->j transfer coin
        snakeLadderPosition.set(13,46);
        snakeLadderPosition.set(27,5);
        snakeLadderPosition.set(33,49);
        snakeLadderPosition.set(40,3);
        snakeLadderPosition.set(42,63);
        snakeLadderPosition.set(43,18);
        snakeLadderPosition.set(50,69);
        snakeLadderPosition.set(54,31);
        snakeLadderPosition.set(62,81);
        snakeLadderPosition.set(66,45);
        snakeLadderPosition.set(76,58);
        snakeLadderPosition.set(74,92);
        snakeLadderPosition.set(89,53);
        snakeLadderPosition.set(99,41);

    }



    public int getXCoordinates (int position){
        return positionCoordinates.get(position).getKey(); //positionCoordinates.get(i).getValue());
    }
//it should be getKey for x coo
    // for y getValue
    public int getYCoordinates (int position){
        return positionCoordinates.get(position).getValue();
    }

    public int getNextPosition(int position){
        if(position>=1 && position<=100)
            return snakeLadderPosition.get(position); // return the new pos after snake or ladder arrive
        else return  -1;
    }

    //to check coordiantes
//    public static void main(String [] args){
//        Board board = new Board();
//        board.populatePositionCoordinates();
//
//        for(int i=0; i< board.positionCoordinates.size(); i++){
//            System.out.println(i+" # x:"+ board.positionCoordinates.get(i).getKey()+ " y:"+
//                    board.positionCoordinates.get(i).getValue());
//        }
//    }
}
