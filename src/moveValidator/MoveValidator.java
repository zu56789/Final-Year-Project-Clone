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
  
  public boolean pieceValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    String piece = board.getPiece(x1,y1).getName();
    
    switch (piece) {
      case "Pawn":
        return pawnValidator(x1,y1,x2,y2,board,whiteMove);
      case "Bishop":
        return bishopValidator(x1,y1,x2,y2,board,whiteMove);
      case "King":
        break;
      case "Queen":
        //run the queen move checker
        break;
      case "Rook":
        return rookValidator(x1,y1,x2,y2,board,whiteMove);
      case "Knight":
        return knightValidator(x1,y1,x2,y2,board,whiteMove);
    }
    
    return false;
  }
  
  public boolean knightValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    return Math.abs(x2-x1) * Math.abs(y2-y1) == 2;
    // add checking for collisions and captures
  }
  
  public boolean pawnValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    if (whiteMove) {
      if (y1 == 6) {
        return (y1-y2) <= 2 && (x1 == x2) && (y1-y2!=0);
        // if first pawn move, can move up to 2 spaces forward
      }
      else {
        return (y1-y2 == 1) && (x1 == x2);
        // if not first move, can only move 1 space forward
      }
    }
    
    else {
      if (y1 == 1) {
        return (y2-y1 <= 2) && (x1 == x2) && (y1-y2!=0);
     // if first pawn move, can move up to 2 spaces forward
      }
      else {
        return (y2-y1 == 1) && (x1 == x2);
     // if not first move, can only move 1 space forward
      }
    }
    
    // will need to change the way of checking if first move
    // add capture logic
    // add collision logic
 
  }
  
  public boolean rookValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {

    if (x1 == x2 && y1 == y2) {
      return false;
  }
    else if (x1 != x2 && y1 != y2) {
      return false;
    }
    else {
      return true;
    }
    
    // add capture logic
    // add collision logic
    
  }
  
  public boolean bishopValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    if (Math.abs(x2-x1) == Math.abs(y2-y1)) {
      if (x1 == x2 && y1 == y2) {
        return false;
      }
      else {
        return true;
      }
    }
    else {
      return false;
    }
    
    // add capture logic
    // add collision logic
    
  }
  
  

}
