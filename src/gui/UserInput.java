package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * this class handles all user input on the board.

 * @author Zuhayr
 *
 */
public class UserInput extends MouseAdapter {
  
  private Board board;
  
  /**
   * constructor for user input.

   * @param board the board class that this user input is used on.
   */
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
