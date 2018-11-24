/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.mew.gameofguess.GUI;

import dev.mew.gameofguess.game.Difficulties;
import dev.mew.gameofguess.game.Difficulty;
import dev.mew.gameofguess.game.Score;
import dev.mew.gameofguess.game.Scores;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author elev
 */
public class HighscoreWindow extends javax.swing.JFrame {

    /**
     * Creates new form HighscoresWindow
     */
    public HighscoreWindow() {
        initComponents();

    }

    public void clear() {
        listModel.clear();

    }

    public void showScores() {
        // We need to generate new averages each time we want to show the score,
        // this in order to get an accurate reading.
        Scores.generateAverages();
        
        for (Difficulty d : Difficulties.difficulties) {
            listModel.addElement("--" + d.name);
            for (Score s : Scores.getScoresByDifficulty(d)) {
                listModel.addElement(s.getScoreData());

            }
            listModel.addElement("\n");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        list = new JList(this.listModel);

        setTitle("Highscores");

        list.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(list);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private DefaultListModel<String> listModel = new DefaultListModel<String>();
    private javax.swing.JList<String> list;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
// </editor-fold>    
}
