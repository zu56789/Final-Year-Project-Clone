package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import pieces.Piece;

/**
 * this class represents the graphical interface of a chess board.

 * @author Zuhayr
 *
 */
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
  
  /**
   * constructor for a chess board.
   */
  public Board() {
    
    this.rows = 8;
    this.columns = 8;
    
    this.tileSize = 65;
    
    this.pieceList = new ArrayList<>();
    
    this.input = new UserInput(this);
    
    this.setPreferredSize(new Dimension(columns * tileSize, rows * tileSize));
    
    this.addMouseListener(input);
    this.addMouseMotionListener(input);
        
  }
  
  
  /**
   * this method returns the piece at a designated tile.

   * @param column int value storing the column of the tile.

   * @param row int value storing the row of the tile.

   * @return returns the piece value at the designated tile.
   */
  public Piece getPiece(int column, int row) {
    
    for (Piece piece : pieceList) {
      if (piece.getRow() == row && piece.getColumn() == column) {
        return piece;
      }
    }
    
    return null;
    
  }
  
  
  /**
   * this method returns the black king on the board.

   * @return piece object containing the black king.
   */
  public Piece getBlackKing() {
    
    for (Piece piece : pieceList) {
      if (piece.getName() == "King" && piece.isBlack()) {
        return piece;
      }
    }
    
    return null;
    
  }
  
  
  /**
   * this method returns the white king on the board.

   * @return piece object containing the white king
   */
  public Piece getWhiteKing() {
      
    for (Piece piece : pieceList) {
      if (piece.getName() == "King" && !piece.isBlack()) {
        return piece;
      }
    }
      
    return null;
      
  }
  
  /**
   * this method returns a list of all black pieces on the board.

   * @return returns the arraylist of black pieces.
   */
  public ArrayList<Piece> getBlackPieces() {
    
    ArrayList<Piece> blackPieces = new ArrayList<>();
    for (Piece piece : pieceList) {
      if (piece.isBlack()) {
        blackPieces.add(piece);
      }
    }
    return blackPieces;
  }
  
  
  /**
   * this method returns a list of all white pieces on the board.

   * @return returns the arraylist of white pieces.
   */
  public ArrayList<Piece> getWhitePieces() {
    
    ArrayList<Piece> whitePieces = new ArrayList<>();
    for (Piece piece : pieceList) {
      if (!piece.isBlack()) {
        whitePieces.add(piece);
      }
    }
    return whitePieces;
  }
  
  
  /**
   * this method draws the pieces of each team onto the board.

   * @param whitePieces arraylist of white pieces.

   * @param blackPieces arraylist of black pieces.
   */
  public void drawPieces(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {
    
    for (Piece piece : whitePieces) {
      pieceList.add(piece);
    }
    
    for (Piece piece : blackPieces) {
      pieceList.add(piece);
    }
    
    this.repaint();
    
  }
  
  
  /**
   * this method paints the graphics onto the screen.
   */
  public void paintComponent(Graphics g) {
    
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        g.setColor((c + r) % 2 == 0 ? new Color(111, 135, 210) : new Color(160, 178, 250));
        g.fillRect(c * this.getTileSize(), r * this.getTileSize(),
             this.getTileSize(), this.getTileSize());
      }
    }
    
    for (Piece piece : pieceList) {
      g.drawImage(piece.getPic(), piece.getColumn() * this.getTileSize(), 
          piece.getRow() * this.getTileSize(), null);
    }
  }
  
  /**
   * this method gets the tile size of the board.

   * @return returns the int tilesize value.
   */
  public int getTileSize() {
    return this.tileSize;
  }
  
  
  /**
   * this method checks if a tile is empty.

   * @param column the int column value of the tile.

   * @param row the int row value of the tile.

   * @return returns a boolean value of if the tile is empty or not.
   */
  public boolean emptyTile(int column, int row) {
    Piece piece = this.getPiece(column, row);
    if (piece == null) {
      return true;
    }   else {
      return false;
    }
  }
  
  /**
   * this method gets the x value stored when the board is clicked.

   * @return returns the pressed x value.
   */
  public int getPressedX() {
    return this.pressedX;
  }
  
  /**
   * this method gets the y value stored when the board is clicked.

   * @return returns the pressed y value.
   */
  public int getPressedY() {
    return this.pressedY;
  }
  
  /**
   * this method sets the pressed x value.

   * @param x the value wanting to be stored as the pressed x value.
   */
  public void setPressedX(int x) {
    this.pressedX = x;
  }
  
  /**
   * this method sets the pressed y value.

   * @param y the value wanting to be stored as the pressed y value.
   */
  public void setPressedY(int y) {
    this.pressedY = y;
  }
  
  /**
   * this method gets the x value stored when the mouse on the board is released.

   * @return returns the released x value.
   */
  public int getReleasedX() {
    return this.releasedX;
  }
  
  /**
   * this method gets the y value stored when the mouse on the board is released.

   * @return returns the released y value.
   */
  public int getReleasedY() {
    return this.releasedY;
  }
  
  /**
   * this method sets the released x value.

   * @param x the value wanting to be stored as the released x value
   */
  public void setReleasedX(int x) {
    this.releasedX = x;
  }
  
  /**
   * this method sets the released y value.

   * @param y the value wanting to be stored as the released y value.
   */
  public void setReleasedY(int y) {
    this.releasedY = y;
  }
  
  /**
   * this method checks if the board has been pressed.

   * @return returns a boolean value of if it has been pressed or not.
   */
  public boolean pressed() {
    return this.pressed;
  }
  
  /**
   * this method sets the pressed boolean value.

   * @param pressed boolean value of if the board has been pressed or not.
   */
  public void setPressed(boolean pressed) {
    this.pressed = pressed;
  }
  
  /**
   * this method checks if the board has been released.

   * @return returns a boolean value of if it has been released or not.
   */
  public boolean released() {
    return this.released;
  }
  
  /**
   * this method sets the released boolean value.

   * @param released boolean value of if the board has been released or not.
   */
  public void setReleased(boolean released) {
    this.released = released;
  }
  
  

}
