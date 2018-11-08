package dev.mew.gameofguess.game;

import java.util.Random;
import java.util.Timer;

/**
 * Holds the game, and handles guessing.
 *
 * @author Mew_
 */
public final class Game {
    private Random randomNumberGenerator = new Random();
    private Difficulty difficulty;

    private long randomNumber;

    /**
     * Initialize game with a difficulty level
     *
     * @param difficulty
     */
    public Game(Difficulty difficulty) {
        changeDifficulty(difficulty);
        generateNewNumber();

    }

    /**
     * Initialize game with the standard EASY difficulty level.
     *
     * @param difficulty
     */
    public Game() {
        changeDifficulty(Difficulty.EASY);
        generateNewNumber();

    }

    public boolean isEqual(long number) {
        if (number == randomNumber) {
            return true;
        }

        return false;

    }

    public boolean higher(long number) {
        // Check if it's higher or lower than the random number.
        if (number > randomNumber) {
            return true;
            
        }

        return false;

    }

    public long getNumber() {
        return randomNumber;

    }

    public void changeDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;

    }

    public void generateNewNumber() {
        this.randomNumber = nextLong(randomNumberGenerator, difficulty.maxNumber);

    }

    /**
     * Fancier version of nextLong, allows clamping- 0 to n.
     * @param rng
     * @param max
     * @return 
     */
    private long nextLong(Random rng, long max) {
        long bits, val;
        do {
            bits = (rng.nextLong() << 1) >>> 1;
            val = bits % max;
        } while (bits - val + (max - 1) < 0L);
        return val;
        
    }

    public void reset() {
        generateNewNumber();

    }
}
