package gui;

import javax.swing.*;
import pieces.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
  
  private final int rows;
  private final int columns;
  private final int tileSize = 65;
  
  private ArrayList<Piece> pieceList = new ArrayList<>();
  
  private Piece selectedPiece;
  private Piece releasedPiece;
  
  private UserInput input;
  
  private int releasedX;
  private int releasedY;
  
  public Board() {
    
    this.rows = 8;
    this.columns = 8;
    
    this.input = new UserInput(this);
    
    this.setPreferredSize(new Dimension(columns * tileSize, rows * tileSize));
    
    this.addMouseListener(input);
    this.addMouseMotionListener(input);
    
    
  }
  
  
  public Piece getPiece(int column, int row) {
    
    for (Piece piece: pieceList) {
      if (piece.getRow() == row && piece.getColumn() == column) {
        return piece;
      }
    }
    
    return null;
    
  }
  
  public ArrayList<Piece> getBlackPieces() {
    
    ArrayList<Piece> blackPieces = new ArrayList<>();
    for (Piece piece: pieceList) {
      if (piece.isBlack()) {
        blackPieces.add(piece);
      }
    }
    return blackPieces;
  }
  
  
  public ArrayList<Piece> getWhitePieces() {
    
    ArrayList<Piece> whitePieces = new ArrayList<>();
    for (Piece piece: pieceList) {
      if (!piece.isBlack()) {
        whitePieces.add(piece);
      }
    }
    return whitePieces;
  }
  
  
  
  
  public void drawPieces(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {
    
    for (Piece piece: whitePieces) {
      pieceList.add(piece);
    }
    
    for (Piece piece: blackPieces) {
      pieceList.add(piece);
    }
    
    this.repaint();
    
  }
  
  
  public void paintComponent(Graphics g) {
    
    for (int r = 0; r< rows; r++) {
      for (int c = 0; c< columns; c++) {
        g.setColor((c+r) %2 == 0 ? new Color(111,135,210) : new Color(160,178,250));
        g.fillRect(c*this.getTileSize(), r*this.getTileSize(), this.getTileSize(), this.getTileSize());
      }
    }
    
    for (Piece piece : pieceList) {
      g.drawImage(piece.getPic(),piece.getColumn()* this.getTileSize(), piece.getRow()* this.getTileSize(), null);
    }
  }
  
  public int getTileSize() {
    return this.tileSize;
  }
  
  public Piece getSelectedPiece() {
    return this.selectedPiece;
  }
  
  
  public void setSelectedPiece(Piece selectedPiece) {
    this.selectedPiece = selectedPiece;
  }
  
  public Piece getReleasedPiece() {
    return this.releasedPiece;
  }
  
  public void setReleasedPiece(Piece releasedPiece) {
    this.releasedPiece = releasedPiece;
  }
  
  public boolean emptyTile(int column, int row) {
    Piece piece = this.getPiece(column, row);
    if (piece == null) {
      return true;
    }
    else {
      return false;
    }
  }
  
  public int getReleasedX() {
    return this.releasedX;
  }
  
  public int getReleasedY() {
    return this.releasedY;
  }
  
  public void setReleasedX(int x) {
    this.releasedX = x;
  }
  
  public void setReleasedY(int y) {
    this.releasedY = y;
  }
  

}
