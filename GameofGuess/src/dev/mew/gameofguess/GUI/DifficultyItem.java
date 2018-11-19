/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.mew.gameofguess.GUI;

import dev.mew.gameofguess.game.Difficulty;
import javax.swing.JMenuItem;

/**
 *
 * @author elev
 */
public class DifficultyItem extends JMenuItem {
    private final Difficulty difficulty;
    
    public DifficultyItem(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.setText(difficulty.name);
        
    }
    
    public Difficulty getDifficulty(){
        return difficulty;
        
    }
}
