package dev.mew.gameofguess.GUI;

import dev.mew.gameofguess.game.Difficulties;
import dev.mew.gameofguess.game.Difficulty;
import dev.mew.gameofguess.game.Game;
import dev.mew.gameofguess.game.Score;
import dev.mew.gameofguess.game.Scores;
import java.awt.event.ActionEvent;

/**
 * Game window where the game takes place
 *
 * @author Mattias Viklund
 */
public class GameWindow extends javax.swing.JFrame {

    // Where to load the difficulties and scores from
    private final String difficultyPath = "difficulties.txt";
    private final String scoresPath = "scores.txt";

    private final Scores highscores = new Scores();
    private final Difficulties difficulties = new Difficulties();

    ///
    // Game Variables
    /// 
    // Game timer
    private long guessTimerMillis = 0;
    // Total guesses made
    private int guessCount = 0;
    // Are we playing?
    private boolean timerRunning = false;

    private final Game game = new Game();
    ///
    //

    /**
     * Creates new form GameWindow along with loading scores and difficulties
     */
    public GameWindow() {
        initComponents();
        loadDifficulties();
        highscores.loadScores(scoresPath);
        guessTextField.requestFocusInWindow();

    }

    private void loadDifficulties() {
        difficulties.loadDifficulties(difficultyPath);

        // Iterate through each of the difficulties and create a button corresponding with difficulty
        for (DifficultyItem menuItem : difficulties.generateButtons()) {
            menuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    // Add action handler to change difficulty when button is pressed
                    changeDifficulty(evt, menuItem.getDifficulty());

                }
            });

            // Add the created item to the menu difficulty button
            menuDifficulty.add(menuItem);

        }
    }

    /**
     * Gets called every time you press enter
     */
    private void sendText() {
        // Sent text
        String text = guessTextField.getText();

        // Reset the "YOU WIN!" label
        statusLabel2.setText("");

        try {
            // Try to parse the number we guessed into a long
            long guessedNumber = Long.parseLong(text);

            // Start timer, and get starting time from the system.
            if (!timerRunning) {
                timerRunning = true;
                guessTimerMillis = System.currentTimeMillis();

            }

            // Guess
            guess(guessedNumber);

        } catch (NumberFormatException e) {
            statusLabel1.setText("That is not a number, try again.");

        }

        guessTextField.setText("");

    }

    private void guess(long number) {
        // The number we need to win is gotten from game
        long winNumber = game.getNumber();
        // Increment total times guessed
        guessCount++;

        // Victory condition
        if (number == winNumber) {
            timerRunning = false;

            // Deltatime from start of game until you finished guessing.
            long timeTakenMillis = System.currentTimeMillis() - guessTimerMillis;

            // Get seconds from milliseconds
            int seconds = (int) (timeTakenMillis / 1000);

            // Whelp! To convert ms into S.ms takes some funny business.
            // So just substring out the last 3 chars out of the string.
            int ms = Integer.parseInt((timeTakenMillis + "").substring((timeTakenMillis + "").length() - 3));

            // Set the labels to WIN-MODE
            statusLabel1.setText("YOU WON IN " + seconds + "." + ms + " SECONDS");
            statusLabel2.setText("Type any number to play again.");

            // Eh, should probably only do this if we get a new high score.
            // But I don't feel like it right now, so just add whatever this score is.
            showAddNewScoreDialog(guessCount, timeTakenMillis);

            // Reset the game for another round.
            guessCount = 0;
            game.reset();

        }

        if (number > winNumber) {
            statusLabel1.setText("Lower");

        } else if (number < winNumber) {
            statusLabel1.setText("Higher");

        }
    }

    /**
     * Change difficulty depending on which menu item was clicked.
     *
     * @param evt
     * @param difficulty
     */
    void changeDifficulty(ActionEvent evt, Difficulty difficulty) {
        game.changeDifficulty(difficulty);

        // Reset all labels
        difficultyLabel.setText("Difficulty: " + difficulty.name + " 1/" + difficulty.maxNumber);
        statusLabel1.setText("");
        statusLabel1.setText("");

        // Reset the game
        guessCount = 0;
        game.reset();

    }

    /**
     * Do we want to add this new score to the high score list?
     *
     * @param guessCount total number of guesses it took
     * @param time in milliseconds
     */
    private void showAddNewScoreDialog(int guessCount, long time) {
        // Create a new score with no name
        Score s = new Score(game.getDifficulty(), guessCount, time, null, false);

        // In order to compare this score to others, we need to generate averages based on it
        Scores.generateAverages();

        // Get score magic number from the Score
        String score = "" + s.getScore() * 100;
        this.newScore = s;

        // Set the score text to it, and show the dialog
        scoreLabel.setText(score);
        newHighscore.setVisible(true);

    }

    /**
     * Show the high scores in a fancy new form
     */
    private void showHighscores() {
        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HighscoreWindow window = new HighscoreWindow();
                window.showScores();
                window.setVisible(true);

            }
        });
    }

    /// We do not touch the holy GUI spaghetti code
// <editor-fold defaultstate="collapsed" desc="Generated Code">   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newHighscore = new javax.swing.JDialog();
        contentPanel = new javax.swing.JPanel();
        scoreLabel = new javax.swing.JLabel();
        yesButton = new javax.swing.JButton();
        addScoreLabel = new javax.swing.JLabel();
        noButton = new javax.swing.JButton();
        nameTextField = new javax.swing.JTextField();
        contentPane = new javax.swing.JPanel();
        gamePanel = new javax.swing.JPanel();
        guessTextField = new javax.swing.JTextField();
        statusLabel1 = new javax.swing.JLabel();
        statusLabel2 = new javax.swing.JLabel();
        guessLabel = new javax.swing.JLabel();
        difficultyLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        fileShowHighscores = new javax.swing.JMenuItem();
        fileQuit = new javax.swing.JMenuItem();
        menuDifficulty = new javax.swing.JMenu();

        newHighscore.setTitle("ADD NEW HIGHSCORE?");
        newHighscore.setMinimumSize(new java.awt.Dimension(513, 300));
        newHighscore.setPreferredSize(new java.awt.Dimension(513, 300));
        newHighscore.setSize(new java.awt.Dimension(513, 300));

        contentPanel.setMinimumSize(new java.awt.Dimension(489, 272));

        scoreLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("0.0025");

        yesButton.setText("YES");
        yesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesButtonActionPerformed(evt);
            }
        });

        addScoreLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addScoreLabel.setText("ADD THIS SCORE TO HIGHSCORE TABLE?");

        noButton.setText("NO");
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonActionPerformed(evt);
            }
        });

        nameTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameTextField.setText("name");
        nameTextField.setToolTipText("Enter name here");

        org.jdesktop.layout.GroupLayout contentPanelLayout = new org.jdesktop.layout.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, contentPanelLayout.createSequentialGroup()
                .add(75, 77, Short.MAX_VALUE)
                .add(addScoreLabel)
                .add(72, 72, 72))
            .add(contentPanelLayout.createSequentialGroup()
                .add(208, 208, 208)
                .add(scoreLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(211, Short.MAX_VALUE))
            .add(contentPanelLayout.createSequentialGroup()
                .add(192, 192, 192)
                .add(contentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(nameTextField)
                    .add(contentPanelLayout.createSequentialGroup()
                        .add(yesButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(noButton)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(contentPanelLayout.createSequentialGroup()
                .add(60, 60, 60)
                .add(addScoreLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(scoreLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 21, Short.MAX_VALUE)
                .add(nameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(contentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(noButton)
                    .add(yesButton))
                .add(37, 37, 37))
        );

        org.jdesktop.layout.GroupLayout newHighscoreLayout = new org.jdesktop.layout.GroupLayout(newHighscore.getContentPane());
        newHighscore.getContentPane().setLayout(newHighscoreLayout);
        newHighscoreLayout.setHorizontalGroup(
            newHighscoreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(newHighscoreLayout.createSequentialGroup()
                .addContainerGap()
                .add(contentPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(11, 11, 11))
        );
        newHighscoreLayout.setVerticalGroup(
            newHighscoreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(newHighscoreLayout.createSequentialGroup()
                .addContainerGap()
                .add(contentPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game of Guess");
        setName("gameWindow"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
                .add(guessLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                .add(57, 57, 57)
                .add(statusLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(statusLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(91, 91, 91))
            .add(gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, gamePanelLayout.createSequentialGroup()
                    .addContainerGap(161, Short.MAX_VALUE)
                    .add(guessTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(161, 161, 161)))
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

        fileShowHighscores.setText("Show Highscores");
        fileShowHighscores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileShowHighscoresActionPerformed(evt);
            }
        });
        menuFile.add(fileShowHighscores);

        fileQuit.setText("Quit");
        fileQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileQuitActionPerformed(evt);
            }
        });
        menuFile.add(fileQuit);

        menuBar.add(menuFile);

        menuDifficulty.setText("Difficulty");
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

    // If the user pressed enter, send whatever is in the textbox
    private void guessTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guessTextFieldKeyReleased
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            sendText();

        }
    }//GEN-LAST:event_guessTextFieldKeyReleased

    // Did we want to quit?
    // If so, save all scores
    private void fileQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileQuitActionPerformed
        highscores.saveAll(scoresPath);
        System.exit(0);

    }//GEN-LAST:event_fileQuitActionPerformed

    // Before we close, save all scores
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        highscores.saveAll(scoresPath);

    }//GEN-LAST:event_formWindowClosing

    // If the button was pressed, show the highscores
    private void fileShowHighscoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileShowHighscoresActionPerformed
        showHighscores();
    }//GEN-LAST:event_fileShowHighscoresActionPerformed

    // BELONGS TO:
    // newHighscore
    // If the yes button was pressed, save the score, along with the name entered
    private void yesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesButtonActionPerformed
        this.newScore.setName(nameTextField.getText());
        this.newScore.addToScoreList();
        newHighscore.setVisible(false);

    }//GEN-LAST:event_yesButtonActionPerformed

    // BELONGS TO:
    // newHighscore
    // If the no button was pressed, just hide the form
    private void noButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonActionPerformed
        newHighscore.setVisible(false);

    }//GEN-LAST:event_noButtonActionPerformed

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

        // Create and display the Game Window
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addScoreLabel;
    private javax.swing.JPanel contentPane;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JMenuItem fileQuit;
    private javax.swing.JMenuItem fileShowHighscores;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JLabel guessLabel;
    private javax.swing.JTextField guessTextField;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDifficulty;
    private javax.swing.JMenu menuFile;
    public static javax.swing.JTextField nameTextField;
    private javax.swing.JDialog newHighscore;
    private javax.swing.JButton noButton;
    public static javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel statusLabel1;
    private javax.swing.JLabel statusLabel2;
    private javax.swing.JButton yesButton;
    // End of variables declaration//GEN-END:variables

    // Temporarily store the newHighscore - score somewhere
    private Score newScore = null;

// </editor-fold>
}
