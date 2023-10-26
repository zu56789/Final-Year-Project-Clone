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
  
  private UserInput input;
  
  public Board() {
    
    this.rows = 8;
    this.columns = 8;
    
    this.input = new UserInput(this);
    
    this.setPreferredSize(new Dimension(columns * tileSize, rows * tileSize));
    
    this.addMouseListener(input);
    this.addMouseMotionListener(input);
    
    drawPieces();
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
  
  
  
  
  public void drawPieces() {
    
    /*
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
    
    pieceList.add(new King(this,4,0,true));
    pieceList.add(new King(this,4,7,false)); 
    */
    
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
  
  
  

}
