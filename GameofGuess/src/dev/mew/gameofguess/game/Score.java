/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.mew.gameofguess.game;

/**
 * Score class, saves which difficulty was used, amount of guesses and time it
 * took
 *
 * @author elev
 */
public class Score implements Comparable<Score> {

    private final Difficulty difficulty;
    private int guesses = 0;
    private long time = 0;
    private String name = "";

    public Score(Difficulty difficulty, int guesses, long time) {
        this.difficulty = difficulty;
        this.guesses = guesses;
        this.time = time;
        this.name = "Mew_";

    }

    public String getSerializedData() {
        return "\"" + difficulty.name + "\"," + guesses + time + ",\"" + name + "\"";

    }
    
    public String getScoreData(){
        return difficulty.name + ", " + guesses + " GUESSES, " + time + "ms, " + name;
        
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
        
    }

    @Override
    public int compareTo(Score t) {
        return Integer.compare(this.guesses, t.guesses);
        
    }
}
