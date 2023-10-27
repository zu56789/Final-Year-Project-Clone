package main;


import java.awt.*;
import javax.swing.JFrame;
import game.GameThread;
import gui.Board;
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
    
    
    Board board = new Board();
    
    frame.add(board);
    
    frame.setVisible(true);
    
    Player player1 = new WhitePlayer("white");
    Player player2 = new BlackPlayer("black");
    
    GameThread game = new GameThread(player1,player2, board);
    
    Thread gameThread = new Thread(game);
    
    gameThread.start();
    
    
    

  }

}
