package gui;

import javax.swing.*;
import pieces.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
  
  private final int rows;
  private final int columns;
  private final int tileSize;
  
  private ArrayList<Piece> pieceList;
  
  private UserInput input;
  
  private int pressedX;
  private int pressedY;
  
  private int releasedX;
  private int releasedY;
  
  private boolean pressed;
  private boolean released;
  
  public Board() {
    
    this.rows = 8;
    this.columns = 8;
    
    this.tileSize = 65;
    
    this.pieceList = new ArrayList<>();
    
    this.input = new UserInput(this);
    
    this.setPreferredSize(new Dimension(columns * tileSize, rows * tileSize));
    
    this.addMouseListener(input);
    this.addMouseMotionListener(input);
    
    //this.pressed = false;
    
    
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
  
  
  public boolean emptyTile(int column, int row) {
    Piece piece = this.getPiece(column, row);
    if (piece == null) {
      return true;
    }
    else {
      return false;
    }
  }
  
  public int getPressedX() {
    return this.pressedX;
  }
  
  public int getPressedY() {
    return this.pressedY;
  }
  
  public void setPressedX(int x) {
    this.pressedX = x;
  }
  
  public void setPressedY(int y) {
    this.pressedY = y;
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
  
  public boolean Pressed() {
    return this.pressed;
  }
  
  public void setPressed(boolean pressed) {
    this.pressed = pressed;
  }
  
  public boolean Released() {
    return this.released;
  }
  
  public void setReleased(boolean released) {
    this.released = released;
  }
  
  

}
