package dev.mew.gameofguess.game;

import java.io.BufferedReader;
import java.util.ArrayList;
import dev.mew.gameofguess.util.Utils;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;

/**
 * Handles scores, along with loading and saving
 *
 * @author Mattias Viklund
 */
public class Scores {

    /**
     * Holds all the scores
     */
    public static final ArrayList<Score> SCORES = new ArrayList<>();
    private final ArrayList<Score> loadedScores = new ArrayList<>();

    /**
     * Load all the scores in the text file at the path
     *
     * @param scoresPath location of scores file
     */
    public void loadScores(String scoresPath) {
        BufferedReader reader = Utils.loadOrCreateFile(scoresPath);

        try {
            String readData = "";
            while (readData != null) {
                readData = reader.readLine();

                if (readData != null) {
                    parseLine(readData);

                }
            }
        } catch (IOException ex) {

        }
    }

    public ArrayList<Score> getScores() {
        return SCORES;

    }

    /**
     * Parse the line to see whether it's a valid score
     *
     * @param line score in text
     * @return boolean success
     */
    private boolean parseLine(String line) {
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
            // Try to match the array up to the variables
            difficulty = splitLine[0];
            guessCount = Integer.parseInt(splitLine[1]);
            timems = Long.parseLong(splitLine[2]);
            name = splitLine[3];

        } catch (NumberFormatException e) {
            // Parsing failed, return
            return false;

        }

        // Try and find difficulty by name.
        Difficulty scoreDifficulty = Difficulties.tryGetDifficultyByName(difficulty);

        // If we find a matching difficulty, add the score to the list.
        if (scoreDifficulty != null) {
            Score s = new Score(scoreDifficulty, guessCount, timems, name, true);
            loadedScores.add(s);
            return true;

        }

        return false;

    }

    /**
     * Save scores to score file
     *
     * @param path Location on disk
     */
    public void saveAll(String path) {
        // Open the scores file for writing
        BufferedWriter writer = Utils.openOrCreateFile("scores.txt");

        for (Score score : SCORES) {

            // If it's one of the already saved scores, we don't save it again.
            boolean write = true;
            for (Score s : loadedScores) {
                if (score.ID == s.ID) {
                    write = false;

                }
            }

            if (write) {
                try {
                    // Write the score in a special format "DIFFICULTY,GUESSES,TIMEINMS,PLAYER" and end the line
                    writer.write(score.getSerializedData() + "\n");

                } catch (IOException ex) {
                    // Ignore it if it just can't be written

                }
            }
        }

        // Close the stream and save whatever is in it
        try {
            writer.flush();
            writer.close();
        } catch (IOException ex) {

        }
    }

    /**
     * Get all scores of a certain difficulty
     *
     * @param difficulty
     * @return
     */
    public static ArrayList<Score> getScoresByDifficulty(Difficulty difficulty) {
        ArrayList<Score> scores = new ArrayList<>();

        // Iterate through every score and compare difficulties
        for (Score score : SCORES) {
            if (score.getDifficulty() == difficulty) {
                scores.add(score);

            }
        }
        // Sort it and return it
        Collections.sort(scores);
        return scores;

    }

    /**
     * For ranking the scores in the comparisons
     */
    public static void generateAverages() {
        // Hold iterations
        int i;
        int totalGuess = 0;
        long totalTime = 0;

        for (i = 0; i < SCORES.size(); i++) {
            Score s = SCORES.get(i);
            totalGuess += s.getGuessCount();
            totalTime += s.getTime();

        }

        Score.guessAverage = totalGuess / i;
        Score.timeAverage = totalTime / i;

    }
}
