package com.silkmanuel.spring.learn_spring_framework;

public class GameRunner {
    private MarioGamer gamer;

    public GameRunner(MarioGamer gamer) {
        this.gamer = gamer;
    }

    public void run(){
        gamer.up();
        gamer.down();
        gamer.left();
        gamer.right();
    }
    
}
