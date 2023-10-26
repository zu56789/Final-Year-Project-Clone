package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pieces.Piece;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;

public class UserInput extends MouseAdapter{
  
  private final Board board;
  
  
  public UserInput(Board board) {
    this.board = board;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

 

  @Override
  public void mousePressed(MouseEvent e) {
    
    int column = e.getX() / board.getTileSize();
    int row = e.getY() / board.getTileSize();
    
    Piece pressedPiece = board.getPiece(column, row);
    
    if (pressedPiece != null) {
      board.setSelectedPiece(pressedPiece);
      System.out.println(pressedPiece.getName() + " " + pressedPiece.getColumn()
          + " " + pressedPiece.getRow());
    }
    
    
    
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  

}
