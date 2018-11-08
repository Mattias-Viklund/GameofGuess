package dev.mew.gameofguess.game;

/**
 * Holds difficulty name and max number.
 * 
 * @author Mew_
 */
public class Difficulty {
    public static Difficulty EASY = new Difficulty("EASY", 100);
    public static Difficulty MEDIUM = new Difficulty("MEDIUM", 1000);
    public static Difficulty HARD = new Difficulty("HARD", 10000);
    
    public String name;
    public long maxNumber;

    public Difficulty(String name, long maxNumber) {
        this.name = name;
        this.maxNumber = maxNumber;

    }
}
