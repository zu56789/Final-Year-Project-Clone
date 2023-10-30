package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pieces.Piece;

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
      board.setReleased(false);
      board.setReleasedPiece(null);
      board.setReleasedX(10);
      board.setReleasedY(10);
      board.setPressed(true);
      Piece pressedPiece = board.getPiece(column, row);
      board.setSelectedPiece(pressedPiece);
      
    }
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    
    int column = e.getX() / board.getTileSize();
    int row = e.getY() / board.getTileSize();
    
    board.setReleased(true);
    
    
    if(!board.emptyTile(column, row)) {
      Piece relPiece = board.getPiece(column, row);
      board.setReleasedPiece(relPiece);
    }
    else {
      
      board.setReleasedX(column);
      board.setReleasedY(row);
      
    }
    
    board.setPressed(false);
    board.setSelectedPiece(null);
    
  } 

}
