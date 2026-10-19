package com.example.tictactoe.model;
public class Game { 
    private String[] board; 
    private String currentPlayer,status,winner; 
    
    public Game(String[] b,String p,String s,String w){
        board=b;
        currentPlayer=p;
        status=s;
        winner=w;
    } 
    
    public String[] getBoard(){
        return board;
    } 
    
    public String getCurrentPlayer(){
        return currentPlayer;
    } 
    
    public String getStatus(){
        return status;
    } 
    
    public String getWinner(){
        return winner;
    } 
}
