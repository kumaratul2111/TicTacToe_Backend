package com.example.tictactoe.controller;

import com.example.tictactoe.model.Game; 
import com.example.tictactoe.service.GameService; 
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.*; 
import org.springframework.web.server.ResponseStatusException; 
import java.util.Map;

@RestController
@RequestMapping("/api/game") 
public class GameController 
{ 
    private final GameService s; 

    public GameController(GameService s){
        this.s=s;
    } 
    
    @GetMapping 
    public Game get(){
        return s.getGame();
    } 
    
    @PostMapping("/move") 
    public Game move(@RequestBody Map<String,Integer> r){
        Integer i=r.get("index");
        if(i==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"index is required");
        try{
            return s.move(i);
        }catch(RuntimeException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    } 
    
    @PostMapping("/reset") 
    public Game reset(){
        return s.reset();
    }
}
