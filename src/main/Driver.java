package main;


import java.awt.*;
import javax.swing.JFrame;
import gui.Board;

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

  }

}
