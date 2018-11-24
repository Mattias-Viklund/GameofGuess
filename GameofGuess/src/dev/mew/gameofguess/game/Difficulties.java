package dev.mew.gameofguess.game;

import dev.mew.gameofguess.GUI.DifficultyItem;
import java.io.BufferedReader;
import java.util.ArrayList;
import dev.mew.gameofguess.util.Utils;

/**
 * Holds and handles difficulties created
 *
 * @author Mattias Viklund
 */
public class Difficulties {

    // List of all difficulties
    public static final ArrayList<Difficulty> difficulties = new ArrayList<Difficulty>();

    // Standard difficulties
    public static final Difficulty EASY = new Difficulty("EASY", 100);
    public static final Difficulty MEDIUM = new Difficulty("MEDIUM", 1000);
    public static final Difficulty HARD = new Difficulty("HARD", 10000);

    /**
     * Do we have a difficulty by the name of difficulty
     *
     * @param difficulty name of difficulty
     * @return
     */
    public static Difficulty tryGetDifficultyByName(String difficulty) {
        for (Difficulty d : difficulties) {
            if (d.name.toLowerCase().equals(difficulty.toLowerCase())) {
                return d;

            }
        }
        return null;

    }

    /**
     * Load difficulties from a text file
     *
     * @param difficultiesPath path to the file
     * @return
     */
    public boolean loadDifficulties(String difficultiesPath) {
        BufferedReader reader = Utils.loadOrCreateFile(difficultiesPath);

        try {
            String readData = "";
            while (readData != null) {
                readData = reader.readLine();

                if (readData != null) {
                    ParseLine(readData);

                }
            }
        } catch (Exception ex) {

        }

        return true;

    }

    // Generate menu items for each of the difficulties loaded
    public DifficultyItem[] generateButtons() {
        DifficultyItem[] items = new DifficultyItem[difficulties.size()];

        int i = 0;
        for (Difficulty difficulty : difficulties) {
            items[i] = new DifficultyItem(difficulty);
            i++;

        }
        return items;

    }

    // Each line should have the structure
    // "DIFFICULTY",TRIES
    private boolean ParseLine(String line) {
        if (line.length() == 0 || line.charAt(0) == '/' || line == null) {
            return false;

        }

        // Split current line array
        String[] splitLine = line.split(",");

        // We should have 2 elements for each line in the scores file,
        // But if we don't just return it here.
        if (splitLine.length != 2) {
            return false;

        }

        String difficulty;
        int guesses;

        try {
            // Match up array elements to the variables
            difficulty = splitLine[0];
            guesses = Integer.parseInt(splitLine[1]);

        } catch (NumberFormatException e) {
            // Parsing failed, return
            return false;

        }

        // Create a new difficulty if an old one doesn't exist.
        if (Difficulties.tryGetDifficultyByName(difficulty) == null) {
            return createNewDifficulty(difficulty, guesses);

        }

        return false;

    }

    public static boolean createNewDifficulty(String name, long chance) {
        Difficulty exists = tryGetDifficultyByName(name);
        if (exists != null) {
            return false;
        }

        Difficulty difficulty = new Difficulty(name, chance);
        return true;

    }
}
