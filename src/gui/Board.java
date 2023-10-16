package gui;

import javax.swing.*;
import pieces.Knight;
import pieces.Piece;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
  
  
  int rows = 8;
  int columns = 8;
  
  public int tileSize = 65;
  
  ArrayList<Piece> pieceList = new ArrayList<>();
  
  public Board() {
    this.setPreferredSize(new Dimension(columns * tileSize, rows * tileSize));
    drawPieces();
  }
  
  
  public void drawPieces() {
    pieceList.add(new Knight(this,2,0,true));
    pieceList.add(new Knight(this,2,7,false));
  }
  
  
  public void paintComponent(Graphics g) {
    
    for (int r = 0; r< rows; r++) {
      for (int c = 0; c< columns; c++) {
        g.setColor((c+r) %2 == 0 ? new Color(95,0,196) : Color.black);
        g.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
      }
    }
    
    for (Piece piece : pieceList) {
      piece.paint(g);
    }
  }

}
