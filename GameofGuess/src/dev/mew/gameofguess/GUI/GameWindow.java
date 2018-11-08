package dev.mew.gameofguess.GUI;

import dev.mew.gameofguess.game.Difficulty;
import dev.mew.gameofguess.game.Game;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Mew_
 */
public class GameWindow extends javax.swing.JFrame {

    private String difficultyPath = "difficulties.txt";
    private ArrayList<Difficulty> difficulties = new ArrayList<>();

    private long guessTimerMillis = 0;
    private boolean timerRunning = false;

    private Game game = new Game();

    /**
     * Creates new form GameWindow
     */
    public GameWindow() {
        initComponents();
        //loadDifficulties();
        guessTextField.requestFocusInWindow();

    }

    private void loadDifficulties() {
        FileReader fileReader = null;

        try {
            // Load the difficulties file
            fileReader = new FileReader(difficultyPath);

        } catch (FileNotFoundException fileNotFound) {
            // If the file wasn't found, 
            // we create it and call this method again.
            generateFile(difficultyPath);
            loadDifficulties();

        }
        BufferedReader reader = new BufferedReader(fileReader);

    }

    private void generateFile(String path) {
        // Create the new file
        File file = new File(path);

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(3000);

        } catch (InterruptedException ex1) {

        }
    }

    private void sendText() {
        String text = guessTextField.getText();

        // Reset the second status label
        statusLabel2.setText("");

        try {
            long guessedNumber = Long.parseLong(text);

            if (!timerRunning) {
                timerRunning = true;
                guessTimerMillis = System.currentTimeMillis();

            }

            guess(guessedNumber);

        } catch (Exception e) {
            statusLabel1.setText("That is not a number, try again.");

        }

        guessTextField.setText("");

    }

    private void guess(long number) {
        long randomNumber = game.getNumber();

        // Victory condition
        if (number == randomNumber) {
            timerRunning = false;

            // Delta time from start of game until you finished guessing.
            long timeTakenMillis = System.currentTimeMillis() - guessTimerMillis;
            
            int seconds = (int) (timeTakenMillis / 1000);

            // Whelp! To convert ms into S.ms takes some funny business.
            // So just substring out the last 3 chars out of the string.
            int ms = Integer.parseInt((timeTakenMillis + "").substring((timeTakenMillis + "").length() - 3));

            statusLabel1.setText("YOU WON IN " + seconds + "." + ms + " SECONDS");
            statusLabel2.setText("Type any number to play again.");
            game.reset();

        }

        if (number > randomNumber) {
            statusLabel1.setText("Lower");

        } else if (number < randomNumber) {
            statusLabel1.setText("Higher");

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

        contentPane = new javax.swing.JPanel();
        gamePanel = new javax.swing.JPanel();
        guessTextField = new javax.swing.JTextField();
        statusLabel1 = new javax.swing.JLabel();
        statusLabel2 = new javax.swing.JLabel();
        guessLabel = new javax.swing.JLabel();
        difficultyLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        fileLoadHighscores = new javax.swing.JMenuItem();
        fileSaveHighscores = new javax.swing.JMenuItem();
        fileHelp = new javax.swing.JMenuItem();
        fileQuit = new javax.swing.JMenuItem();
        menuDifficulty = new javax.swing.JMenu();
        difficultyEasy = new javax.swing.JMenuItem();
        difficultyMedium = new javax.swing.JMenuItem();
        difficultyHard = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game of Guess");
        setName("gameWindow"); // NOI18N

        guessTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        guessTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        guessTextField.setMinimumSize(new java.awt.Dimension(100, 23));
        guessTextField.setName(""); // NOI18N
        guessTextField.setPreferredSize(new java.awt.Dimension(100, 23));
        guessTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                guessTextFieldKeyReleased(evt);
            }
        });

        statusLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        statusLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        statusLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        statusLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel2.setToolTipText("");
        statusLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        guessLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        guessLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        guessLabel.setText("Guess the Number");
        guessLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        difficultyLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        difficultyLabel.setText("Difficulty: EASY 1/100");

        org.jdesktop.layout.GroupLayout gamePanelLayout = new org.jdesktop.layout.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(guessLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, gamePanelLayout.createSequentialGroup()
                .add(226, 226, 226)
                .add(gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(statusLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(statusLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(226, 226, 226))
            .add(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(difficultyLabel)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, gamePanelLayout.createSequentialGroup()
                    .add(152, 152, 152)
                    .add(guessTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(152, 152, 152)))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(difficultyLabel)
                .add(88, 88, 88)
                .add(guessLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(57, 57, 57)
                .add(statusLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(statusLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(gamePanelLayout.createSequentialGroup()
                    .add(161, 161, 161)
                    .add(guessTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(161, Short.MAX_VALUE)))
        );

        org.jdesktop.layout.GroupLayout contentPaneLayout = new org.jdesktop.layout.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .add(gamePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .add(gamePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuFile.setText("File");

        fileLoadHighscores.setText("Load Highscores");
        fileLoadHighscores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileLoadHighscoresActionPerformed(evt);
            }
        });
        menuFile.add(fileLoadHighscores);

        fileSaveHighscores.setText("Save Highscores");
        menuFile.add(fileSaveHighscores);

        fileHelp.setText("Help");
        menuFile.add(fileHelp);

        fileQuit.setText("Quit");
        fileQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileQuitActionPerformed(evt);
            }
        });
        menuFile.add(fileQuit);

        menuBar.add(menuFile);

        menuDifficulty.setText("Difficulty");

        difficultyEasy.setText("EASY");
        difficultyEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                difficultyEasyActionPerformed(evt);
            }
        });
        menuDifficulty.add(difficultyEasy);

        difficultyMedium.setText("MEDIUM");
        difficultyMedium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                difficultyMediumActionPerformed(evt);
            }
        });
        menuDifficulty.add(difficultyMedium);

        difficultyHard.setText("HARD");
        difficultyHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                difficultyHardActionPerformed(evt);
            }
        });
        menuDifficulty.add(difficultyHard);

        menuBar.add(menuDifficulty);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(contentPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, contentPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void difficultyEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_difficultyEasyActionPerformed
        game.changeDifficulty(Difficulty.EASY);
        difficultyLabel.setText("Difficulty: EASY 1/100");
        game.reset();

    }//GEN-LAST:event_difficultyEasyActionPerformed

    private void difficultyMediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_difficultyMediumActionPerformed
        game.changeDifficulty(Difficulty.MEDIUM);
        difficultyLabel.setText("Difficulty: MEDIUM 1/1000");
        game.reset();
    }//GEN-LAST:event_difficultyMediumActionPerformed

    private void difficultyHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_difficultyHardActionPerformed
        game.changeDifficulty(Difficulty.HARD);
        difficultyLabel.setText("Difficulty: HARD 1/10000");
        game.reset();

    }//GEN-LAST:event_difficultyHardActionPerformed

    private void guessTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guessTextFieldKeyReleased
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            sendText();

        }
    }//GEN-LAST:event_guessTextFieldKeyReleased

    private void fileQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileQuitActionPerformed
        System.exit(0);

    }//GEN-LAST:event_fileQuitActionPerformed

    private void fileLoadHighscoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileLoadHighscoresActionPerformed


    }//GEN-LAST:event_fileLoadHighscoresActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPane;
    private javax.swing.JMenuItem difficultyEasy;
    private javax.swing.JMenuItem difficultyHard;
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JMenuItem difficultyMedium;
    private javax.swing.JMenuItem fileHelp;
    private javax.swing.JMenuItem fileLoadHighscores;
    private javax.swing.JMenuItem fileQuit;
    private javax.swing.JMenuItem fileSaveHighscores;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JLabel guessLabel;
    private javax.swing.JTextField guessTextField;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDifficulty;
    private javax.swing.JMenu menuFile;
    private javax.swing.JLabel statusLabel1;
    private javax.swing.JLabel statusLabel2;
    // End of variables declaration//GEN-END:variables

// </editor-fold>
}