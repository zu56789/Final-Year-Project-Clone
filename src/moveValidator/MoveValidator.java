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
          return pieceValidator(x1,y1,x2,y2,board, whiteMove);
        }
      }
      
      else {
        
        if (!board.getPiece(x1, y1).isBlack()) {
          return false;
        }
        
        else {
          return pieceValidator(x1,y1,x2,y2,board, whiteMove);
        }
        
        
      }
      
    }

  }
  
  public boolean pieceValidator(int x1, int y1, int x2, int y2, Board board, boolean team) {
    
    String piece = board.getPiece(x1,y1).getName();
    
    switch (piece) {
      case "Pawn":
        // run the pawn move checker
        break;
      case "Bishop":
        //run the bishop move checker
        break;
      case "King":
        break;
      case "Queen":
        //run the queen move checker
        break;
      case "Rook":
        //run the rook move checker
        break;
      case "Knight":
        return knightValidator(x1,y1,x2,y2,board,team);
    }
    
    return false;
  }
  
  public boolean knightValidator(int x1, int y1, int x2, int y2, Board board, boolean team) {
    return Math.abs(x2-x1) * Math.abs(y2-y1) == 2;
  }
  
  

}
