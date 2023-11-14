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
    
    if (move.getx1() >= 8 || move.getx2() >=8 || move.gety1() >= 8 || move.gety2() >=8) {
      return false; // x and y values should be from 0 to 7
    }
    
    else {
      return true;
    }
    
  }
  
  
  
  

}
