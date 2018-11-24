package dev.mew.gameofguess.GUI;

import dev.mew.gameofguess.game.Difficulty;
import javax.swing.JMenuItem;

/**
 * Menu Item difficulty container
 *
 * @author Mew_
 */
public class DifficultyItem extends JMenuItem {

    private final Difficulty difficulty;

    // Give this item a difficulty, and assign the name of the difficulty
    // as the item text.
    public DifficultyItem(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.setText(difficulty.name);

    }

    public Difficulty getDifficulty() {
        return difficulty;

    }
}
