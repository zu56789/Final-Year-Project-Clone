package main;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
  
  
  int rows = 8;
  int columns = 8;
  
  public int tileSize = 65;
  
  public Board() {
    this.setPreferredSize(new Dimension(columns * tileSize, rows * tileSize));
  }
  
  
  public void paintComponent(Graphics g) {
    
    for (int r = 0; r< rows; r++) {
      for (int c = 0; c< columns; c++) {
        g.setColor((c+r) %2 == 0 ? Color.blue : Color.DARK_GRAY);
        g.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
      }
    }
  }

}
