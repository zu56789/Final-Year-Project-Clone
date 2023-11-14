package moveValidator;

import gui.Board;
import move.Move;

public class MoveValidator {
  
  private MoveValidator() {}
  
  private static class Helper {
    private static final MoveValidator INSTANCE = new MoveValidator();
  }
  
  public static MoveValidator getInstance() {
    return Helper.INSTANCE;
  }
  
  public synchronized boolean validMove(Move move, Board board, boolean whiteMove) {
    
    int x1 = move.getx1();
    int x2 = move.getx2();
    int y1 = move.gety1();
    int y2 = move.gety2();
    
    if (x1 >= 8 || x2 >=8 || y1 >= 8 || y2 >=8) {
      return false; // x and y values should be from 0 to 7
    }
    
    else {
      
      if (whiteMove) {
        
        if (board.getPiece(x1, y1).isBlack()) {
          return false;
        }
        
        else {
          //
        }
      }
      
      else {
        
        if (!board.getPiece(x1, y1).isBlack()) {
          return false;
        }
        
        else {
          //
        }
        
        
      }
      
    }
    
    return true;
    
  }
  
  
  
  

}
