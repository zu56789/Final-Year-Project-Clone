package main;

import game.GameThread;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import movevalidator.MoveValidator;
import player.BlackPlayer;
import player.Player;
import player.WhitePlayer;

/**
 * this class is used to create each game and display it to the players.

 * @author Zuhayr
 *
 */
public class Driver {

  public static void main(String[] args) {
    
    MoveValidator moveValidator = MoveValidator.getInstance();
    
    String input = JOptionPane.showInputDialog("How many games do you want to play (up to 4)?");
    int numberOfGames;
    try {
      numberOfGames = Math.min(Integer.parseInt(input), 4);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Starting 1 game by default.");
      numberOfGames = 1;
    }
    
    
    
    JFrame frame = new JFrame("Chess Environment" + "(" + numberOfGames + ")");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    
    int totalWidth = 525 * numberOfGames;
    int height = 527;
    
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(totalWidth, height));
    panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
    
    
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    
    
    for (int i = 0; i < numberOfGames; i++) {
      
      String whitePlayerName = JOptionPane.showInputDialog(frame,
          "Enter name for White Player for Game " + (i + 1) + ":");
      if (whitePlayerName == null || whitePlayerName.trim().isEmpty()) {
        whitePlayerName = "White Player " + (i + 1);
      }

      String blackPlayerName = JOptionPane.showInputDialog(frame,
          "Enter name for Black Player for Game " + (i + 1) + ":");
      if (blackPlayerName == null || blackPlayerName.trim().isEmpty()) {
        blackPlayerName = "Black Player " + (i + 1);
      }
      
      
      
      Player player1 = new WhitePlayer(whitePlayerName);
      Player player2 = new BlackPlayer(blackPlayerName);
      GameThread game = new GameThread(player1, player2, moveValidator);
      // each thread shares the same move validator
      Thread gameThread = new Thread(game);
      gameThread.start();
      panel.add(game.getBoard());
    }
    
    frame.add(scrollPane);
    frame.pack();
    
    if (frame.getWidth() > Toolkit.getDefaultToolkit().getScreenSize().width
        || numberOfGames == 1) {
      frame.setSize(new Dimension(534, 577));
    }
    
    
    frame.setVisible(true);
  }

}
