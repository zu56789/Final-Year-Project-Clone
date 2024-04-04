package main;

import game.GameThread;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import player.BlackPlayer;
import player.Player;
import player.WhitePlayer;

public class Driver {

  public static void main(String[] args) {
    
    
    String input = JOptionPane.showInputDialog("How many games do you want to play (up to 4)?");
    int numberOfGames;
    try {
        numberOfGames = Math.min(Integer.parseInt(input),4);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Starting 1 game by default.");
        numberOfGames = 1;
    }
    
    int totalWidth = 534 * numberOfGames;
    int height = 577;
    
    JFrame frame = new JFrame();
    frame.setTitle("Chess Environment");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(totalWidth, height));
    panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
    
    
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    
    
    for (int i = 0; i < numberOfGames; i++) {
      Player player1 = new WhitePlayer("white");
      Player player2 = new BlackPlayer("black");
      GameThread game = new GameThread(player1, player2);
      Thread gameThread = new Thread(game);
      gameThread.start();
      panel.add(game.getBoard());
    }
    
    frame.add(scrollPane);
    frame.pack();
    
    if (frame.getWidth() > Toolkit.getDefaultToolkit().getScreenSize().width || numberOfGames == 1) {
      frame.setSize(new Dimension(534, 577));
    }
    
    
    frame.setVisible(true);
    
    
    

  }

}
