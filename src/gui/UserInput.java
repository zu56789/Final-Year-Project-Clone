package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pieces.Piece;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;

public class UserInput extends MouseAdapter{
  
  private Board board;
  
  
  public UserInput(Board board) {
    this.board = board;
  }
 
  @Override
  public void mousePressed(MouseEvent e) {
    
    
    
    int column = e.getX() / board.getTileSize();
    int row = e.getY() / board.getTileSize();
    
    if(!board.emptyTile(column, row)) {
      Piece pressedPiece = board.getPiece(column, row);
      board.setSelectedPiece(pressedPiece);
      board.setPressed(true);
    }
    
    /*Piece pressedPiece = board.getPiece(column, row);
    
    if (pressedPiece != null) {
      board.setSelectedPiece(pressedPiece);
      System.out.println(pressedPiece.getName() + " " + pressedPiece.getColumn()
          + " " + pressedPiece.getRow()); 
    } */
    
    //System.out.println("Pressed at column "+ column + " And Row " + row);
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    
    board.setReleased(true);
    
    int column = e.getX() / board.getTileSize();
    int row = e.getY() / board.getTileSize();
    
    
    if(!board.emptyTile(column, row)) {
      board.setReleasedEmpty(false);
      Piece relPiece = board.getPiece(column, row);
      board.setSelectedPiece(relPiece);
    }
    else {
      
      board.setReleasedEmpty(true);
      
      board.setReleasedX(column);
      board.setReleasedY(row);
      
    }
    
    
    /*Piece relPiece = board.getPiece(column, row);
    
    if(relPiece != null) {
      board.setReleasedPiece(relPiece);
    }
    
    System.out.println("Released at column " + column + " And Row " + row);
    */
    
  } 

  

}
