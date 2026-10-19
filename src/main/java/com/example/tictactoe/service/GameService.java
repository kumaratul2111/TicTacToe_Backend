package com.example.tictactoe.service;
import com.example.tictactoe.model.Game; 
import org.springframework.stereotype.Service; 
import java.util.Arrays;

@Service 
public class GameService { 

    private final String[] board=new String[9]; 
    private String currentPlayer="X",
        status="IN_PROGRESS",
        winner=null; 
    
    public GameService(){
        reset();
    }

    public synchronized Game getGame(){
        return snapshot();
    }

    public synchronized Game move(int i){
        if(i<0||i>8)
            throw new IllegalArgumentException("Index must be between 0 and 8"); 
        
        if(!"IN_PROGRESS".equals(status))
            throw new IllegalStateException("Game is already over"); 
        
        if(board[i]!=null)
            throw new IllegalArgumentException("Cell is already occupied"); 
        
        board[i]=currentPlayer; 
        if(win(currentPlayer)){
            status="WON";winner=currentPlayer;
        } 
        
        else if(Arrays.stream(board).allMatch(x->x!=null))
            status="DRAW"; 
        
        else 
            currentPlayer=currentPlayer.equals("X")?"O":"X"; 
        
        return snapshot();
    }

    public synchronized Game reset(){
        Arrays.fill(board,null);
        currentPlayer="X";
        status="IN_PROGRESS";
        winner=null;
        return snapshot();
    }

    private boolean win(String p){
        int[][] l={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int[] a:l)
            if(p.equals(board[a[0]])&&p.equals(board[a[1]])&&p.equals(board[a[2]]))
                return true;
            return false;
        } 
        
    private Game snapshot(){
        return new Game(board.clone(),currentPlayer,status,winner);
    } 
}
