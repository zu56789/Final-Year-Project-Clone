package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInput extends MouseAdapter{
  
  private Board board;
  
  public UserInput(Board board) {
    this.board = board;
  }
 
  @Override
  public void mousePressed(MouseEvent e) {
    
    board.setReleased(false);
    
    board.setPressed(true);
    
    int column = e.getX() / board.getTileSize();
    int row = e.getY() / board.getTileSize();
    
    
    
    board.setPressedX(column);
    board.setPressedY(row);
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    
    board.setReleased(true);
    
    
    int column = e.getX() / board.getTileSize();
    int row = e.getY() / board.getTileSize();
    
    board.setReleasedX(column);
    board.setReleasedY(row);
    
    
    
    board.setPressed(false);
    
    
    
  } 

}
