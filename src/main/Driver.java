package main;


import java.awt.*;
import javax.swing.JFrame;
import game.GameThread;
import player.BlackPlayer;
import player.Player;
import player.WhitePlayer;

public class Driver {

  public static void main(String[] args) {
    
    JFrame frame = new JFrame();
    frame.setTitle("Chess Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridBagLayout());
    frame.setMinimumSize(new Dimension(1000, 600));
    frame.setLocationRelativeTo(null);
    
    
    Player player1 = new WhitePlayer("white");
    Player player2 = new BlackPlayer("black");
    
    GameThread game = new GameThread(player1,player2);
    
    Thread gameThread = new Thread(game);
    
    gameThread.start();
    
    
    frame.add(game.getBoard());
    
    frame.setVisible(true);
    
    
    

  }

}
