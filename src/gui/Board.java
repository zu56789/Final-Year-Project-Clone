package gui;

import javax.swing.*;
import pieces.*;
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
    pieceList.add(new Knight(this,1,0,true));
    pieceList.add(new Knight(this,6,0,true));
    
    pieceList.add(new Knight(this,1,7,false));
    pieceList.add(new Knight(this,6,7,false));
    
    pieceList.add(new Pawn(this,0,1,true));
    pieceList.add(new Pawn(this,1,1,true));
    pieceList.add(new Pawn(this,2,1,true));
    pieceList.add(new Pawn(this,3,1,true));
    pieceList.add(new Pawn(this,4,1,true));
    pieceList.add(new Pawn(this,5,1,true));
    pieceList.add(new Pawn(this,6,1,true));
    pieceList.add(new Pawn(this,7,1,true));
    
    pieceList.add(new Pawn(this,0,6,false));
    pieceList.add(new Pawn(this,1,6,false));
    pieceList.add(new Pawn(this,2,6,false));
    pieceList.add(new Pawn(this,3,6,false));
    pieceList.add(new Pawn(this,4,6,false));
    pieceList.add(new Pawn(this,5,6,false));
    pieceList.add(new Pawn(this,6,6,false));
    pieceList.add(new Pawn(this,7,6,false));
    
    
    pieceList.add(new Rook(this,0,0,true));
    pieceList.add(new Rook(this,7,0,true));
    
    pieceList.add(new Rook(this,7,7,false));
    pieceList.add(new Rook(this,0,7,false));
    
    pieceList.add(new Bishop(this,2,0,true));
    pieceList.add(new Bishop(this,5,0,true));
    
    pieceList.add(new Bishop(this,2,7,false));
    pieceList.add(new Bishop(this,5,7,false));
    
    pieceList.add(new Queen(this,3,0,true));
    
    pieceList.add(new Queen(this,3,7,false));
    
  }
  
  
  public void paintComponent(Graphics g) {
    
    for (int r = 0; r< rows; r++) {
      for (int c = 0; c< columns; c++) {
        g.setColor((c+r) %2 == 0 ? new Color(111,135,210) : new Color(160,178,250));
        g.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
      }
    }
    
    for (Piece piece : pieceList) {
      piece.paint(g);
    }
  }

}
