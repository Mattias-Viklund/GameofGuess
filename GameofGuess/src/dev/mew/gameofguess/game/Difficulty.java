package dev.mew.gameofguess.game;

/**
 * Holds difficulty name and max number.
 *
 * @author Mew_
 */
public class Difficulty {

    public String name;
    public long maxNumber;

    public Difficulty(String name, long maxNumber) {
        this.name = name;
        this.maxNumber = maxNumber;

        // MACRO:
        // Add this to the Difficulties.difficulties list.
        Difficulties.difficulties.add(this);

    }
}
