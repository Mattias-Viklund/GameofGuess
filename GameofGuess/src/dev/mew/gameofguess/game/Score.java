package dev.mew.gameofguess.game;

/**
 * Score class, saves which difficulty was used, amount of guesses and time it
 * took
 *
 * @author Mattias Viklund
 */
public class Score implements Comparable<Score> {

    // Give each score an ID, it's important for something I can't even rememer anymore
    private static int IDs = 0;

    // Difficulty associated with the score
    private final Difficulty difficulty;

    private final int guessCount;
    private final long time;
    private String name;
    public final int ID;

    public Score(Difficulty difficulty, int guessCount, long time, String name, boolean macro) {
        this.difficulty = difficulty;
        this.guessCount = guessCount;
        this.time = time;
        
        // If it doesn't have a name, name it unknown
        if (name == null) {
            this.name = "unknown";
        } else {
            this.name = name;
        }

        this.ID = IDs;
        IDs++;

        // MACRO:
        // Add this to the SCORES list
        if (macro) {
            addToScoreList();

        }
    }

    // Add this score to the static score table
    public void addToScoreList() {
        Scores.SCORES.add(this);

    }

    public void setName(String name) {
        this.name = name;

    }

    /**
     * Get score data in the format of "<DIFFICULTY>,<GUESSCOUNT>,<TIME>,<NAME>"
     *
     * @return String data
     */
    public String getSerializedData() {
        return difficulty.name + "," + guessCount + "," + time + "," + name;

    }

    /**
     * Get score data in the format of "<GUESSCOUNT> GUESSES in <TIME>(ms) by
     * <NAME>"
     *
     * @return String data
     */
    public String getScoreData() {
        return guessCount + " GUESSES in " + time + "(ms) by " + name;

    }

    public Difficulty getDifficulty() {
        return this.difficulty;

    }

    public int getGuessCount() {
        return guessCount;

    }

    public long getTime() {
        return time;

    }

    // Same formula as compareTo
    public float getScore() {
        return (float) ((long) (guessAverage / this.guessCount)
                * guessWeight / (timeAverage / this.time) * timeWeight);

    }

    //
    // Weighted score system
    // Prioritize number of guesses and then the time taken
    public static final float guessWeight = 0.25f;
    public static final float timeWeight = 0.75f;

    // Average number of guesses and time per correct answer
    public static int guessAverage = 0;
    public static long timeAverage = 0;

    @Override
    // Method of comparison between two elements.
    // We want to prioritize low guesses and low times.
    // Those should have higher scores than high guesses and high time.    
    public int compareTo(Score other) {
        // Formula
        // guessScore = (guessAverage / guessCount) * guessWeight 
        // timeScore = (timeAverage / timeCount) * timeWeight 
        // Add them together and let Integer.Compare compare them.

        int thisSum = (int) ((long) (guessAverage / this.guessCount)
                * guessWeight + (timeAverage / this.time) * timeWeight);

        int otherSum = (int) ((long) (guessAverage / other.guessCount)
                * guessWeight + (timeAverage / other.time) * timeWeight);

        return Integer.compare(thisSum, otherSum);

    }
}
