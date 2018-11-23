/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.mew.gameofguess.game;

import dev.mew.gameofguess.GUI.DifficultyItem;
import java.io.BufferedReader;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import dev.mew.gameofguess.util.Utils;

/**
 * Holds and handles difficulties created
 *
 * @author elev
 */
public class Difficulties {

    public static final ArrayList<Difficulty> difficulties = new ArrayList<Difficulty>();

    public static final Difficulty EASY = new Difficulty("EASY", 100);
    public static final Difficulty MEDIUM = new Difficulty("MEDIUM", 1000);
    public static final Difficulty HARD = new Difficulty("HARD", 10000);

    public static Difficulty getDifficultyByName(String difficulty) {
        for (Difficulty d : difficulties) {
            if (d.name.toLowerCase().equals(difficulty.toLowerCase())) {
                return d;
            }

        }
        return null;

    }

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
            difficulty = splitLine[0];
            guesses = Integer.parseInt(splitLine[1]);

        } catch (NumberFormatException e) {
            // Parsing failed, return
            return false;

        }

        // Create a new difficulty if an old one doesn't exist.
        if (Difficulties.getDifficultyByName(difficulty) == null) {
            Difficulty newDifficulty = new Difficulty(difficulty, guesses);
            return true;

        }

        return false;

    }

    public static boolean createNewDifficulty(String name, long chance) {
        Difficulty exists = getDifficultyByName(name);
        if (exists != null) {
            return false;
        }

        Difficulty difficulty = new Difficulty(name, chance);
        return true;

    }
}
