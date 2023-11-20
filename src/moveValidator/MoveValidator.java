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
    
    else if (board.getPiece(x1, y1) == null) {
      return false;
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
        return kingValidator(x1,y1,x2,y2,board,whiteMove);
      case "Queen":
        return queenValidator(x1,y1,x2,y2,board,whiteMove);
      case "Rook":
        return rookValidator(x1,y1,x2,y2,board,whiteMove);
      case "Knight":
        return knightValidator(x1,y1,x2,y2,board,whiteMove);
    }
    
    return false;
  }
  
  public boolean knightValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    if (sameTeam(x1,y1,x2,y2,board)) {
      return false;
    }
    
    else {
      
      return Math.abs(x2-x1) * Math.abs(y2-y1) == 2;
    }
    
    // add checking for captures
  }
  
  public boolean pawnValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    
    if (sameTeam(x1,y1,x2,y2,board) || collision(x1,y1,x2,y2,board)) {
      return false;
    }
    
    else if (whiteMove) {
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
 
  }
  
  public boolean rookValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    
    if (sameTeam(x1,y1,x2,y2,board) || collision(x1,y1,x2,y2,board)) {
      return false;
    }
    
    

    else if (x1 == x2 && y1 == y2) {
      return false;
  }
    
    else if (x1 != x2 && y1 != y2) {
      return false;
    }
    else {
      return true;
    }
    
    // add capture logic
    
  }
  
  public boolean bishopValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    
    if (sameTeam(x1,y1,x2,y2,board) || collision(x1,y1,x2,y2,board)) {
      return false;
    }
    
    
    
    
    else if (Math.abs(x2-x1) == Math.abs(y2-y1)) {
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
    
  }
  
  public boolean queenValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    return bishopValidator(x1,y1,x2,y2,board,whiteMove) || rookValidator(x1,y1,x2,y2,board,whiteMove);
    
    
  }
  
  public boolean kingValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    if (sameTeam(x1,y1,x2,y2,board) || collision(x1,y1,x2,y2,board)) {
      return false;
    }
    
    
    
    else if (x1 == x2 && y1 == y2) {
      return false;
    }
    
    else {
      
      return Math.abs((x2-x1) * (y2-y1)) == 1 || Math.abs(x2-x1) + Math.abs(y2-y1) == 1;
      
    }
    
    // add capture logic
    // add check/checkmate logic to not allow certain moves
  }
  
  public boolean sameTeam(int x1, int y1, int x2, int y2, Board board) {
    
    if (board.getPiece(x1, y1) != null && board.getPiece(x2,y2) != null) {
      return board.getPiece(x1, y1).isBlack() == board.getPiece(x2, y2).isBlack();
    }
    else {
      return false;
    }
    
    
    
  }
  
  public boolean collision(int x1, int y1, int x2, int y2, Board board) {
    
    int dx = Integer.compare(x2, x1);
    int dy = Integer.compare(y2, y1);
    
    int xcurr = x1 + dx;
    int ycurr = y1+ dy;
    
    while(xcurr != x2 || ycurr!= y2) {
      if (board.getPiece(xcurr, ycurr) != null) {
        return true;
      }
      
      xcurr += dx;
      ycurr += dy;
    }
    return false;
  }
  
  
  public boolean capture(int x1, int y1, int x2, int y2, Board board) {
    
    if (board.getPiece(x1, y1) != null && board.getPiece(x2,y2) != null) {
      return board.getPiece(x1, y1).isBlack() != board.getPiece(x2, y2).isBlack();
    }
    else {
      return false;
    }
  }
  
  
  

}
