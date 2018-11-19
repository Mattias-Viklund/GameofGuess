package dev.mew.gameofguess.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import dev.mew.gameofguess.util.Utils;
import java.util.Arrays;
import java.util.Collections;

/**
 * Handles scores, along with loading and saving
 *
 * @author elev
 */
public class Scores {

    public static final ArrayList<Score> scores = new ArrayList<Score>();

    public Scores() {

    }
    
    public ArrayList<Score> getScores(){
        return scores;
        
    }

    public boolean loadScores(String scoresPath) {
        BufferedReader reader = Utils.loadOrCreateFile(scoresPath);

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

    // Each line should have the structure
    // "DIFFICULTY",TRIES,TIMEINMS,"NAME"
    private boolean ParseLine(String line) {
        if (line.length() == 0 || line.charAt(0) == '/' || line == null) {
            return false;
        }

        // Split current line into array
        String[] splitLine = line.split(",");

        // We should have 4 elements for each line in the scores file,
        // But if we don't just return it here.
        if (splitLine.length != 4) {
            return false;

        }

        String difficulty;
        int guessCount;
        long timems;
        String name;

        try {
            difficulty = splitLine[0];
            guessCount = Integer.parseInt(splitLine[1]);
            timems = Long.parseLong(splitLine[2]);
            name = splitLine[3];

        } catch (NumberFormatException e) {
            // Parsing failed, return
            return false;

        }

        // Try and find difficulty by name.
        Difficulty scoreDifficulty = Difficulties.getDifficultyByName(difficulty);

        // If we didn't find a difficulty, don't add it to the list.
        if (scoreDifficulty != null) {
            Score s = new Score(scoreDifficulty, guessCount, timems);
            scores.add(s);

        }

        return true;

    }

    /**
     * Save scores to score file
     *
     * @param path Location on disk
     * @param playerName Name of player
     */
    public void saveAll(String path, String playerName) {
        InputStreamReader isr;
        try {
            isr = new FileReader(path);
            BufferedReader reader = new BufferedReader(isr);
        } catch (FileNotFoundException ex) {

        }

        for (Score score : scores) {

        }
    }

    public void saveNewScore(Score score) {

    }
    
    public static ArrayList<Score> getScoresByDifficulty(Difficulty d){
        ArrayList<Score> sc = new ArrayList<Score>();
        
        for (Score s : scores)
        {
            if (s.getDifficulty() == d)
                sc.add(s);
            
        }
        Collections.sort(sc);
        
        return sc;
        
    }
}
