package movevalidator;

import gui.Board;
import move.Move;
import pieces.Piece;

/**
 * this class validates movements made in a chess game.

 * @author Zuhayr
 *
 */
public class MoveValidator {
  
  private MoveValidator() {}
  
  private static class Helper {
    private static final MoveValidator INSTANCE = new MoveValidator();
    // singleton class
  }
  
  public static MoveValidator getInstance() {
    return Helper.INSTANCE;
    // class only instantiated when this is run
  }
  
  /**
   * this method validates if a move is legal or not.

   * @param move the movement being validated.

   * @param board the board in which the movement will take place.

   * @param whiteMove boolean value used to determine which player is moving.

   * @return returns a boolean value used to determine if the movement is legal or not.
   */
  public boolean validMove(Move move, Board board, boolean whiteMove) {
    
    int x1 = move.getx1();
    int x2 = move.getx2();
    int y1 = move.gety1();
    int y2 = move.gety2();
    
    if (this.isKingChecked(x1, y1, x2, y2, board, whiteMove)) {
      return false;
    }
    
    
    if ((x1 > 7  || x1 < 0) || (x2 > 7 || x2 < 0) || (y1 > 7 || y2 < 0) || (y2 > 7 || y2 < 0)) {
      return false; // x and y values should be from 0 to 7
    }   else if (board.getPiece(x1, y1) == null) {
      return false;
    }   else {
      
      if (whiteMove) {
        
        if (board.getPiece(x1, y1).isBlack()) {
          return false;
        }   else {
          return pieceValidator(x1, y1, x2, y2, board, whiteMove);
        }
      } else {
        
        if (!board.getPiece(x1, y1).isBlack()) {
          return false;
        }   else {
          return pieceValidator(x1, y1, x2, y2, board, whiteMove);
        }
        
      }
      
    }

  }
  
  /**
   * this method validates which type of piece is being moved.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement will take place on.

   * @param whiteMove boolean value used to determine which player is moving.

   * @return returns a boolean value determining if the piece has moved in a legal way.
   */
  public boolean pieceValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    String piece = board.getPiece(x1, y1).getName();
    
    switch (piece) {
      case "Pawn":
        return pawnValidator(x1, y1, x2, y2, board, whiteMove);
      case "Bishop":
        return bishopValidator(x1, y1, x2, y2, board, whiteMove);
      case "King":
        return kingValidator(x1, y1, x2, y2, board, whiteMove);
      case "Queen":
        return queenValidator(x1, y1, x2, y2, board, whiteMove);
      case "Rook":
        return rookValidator(x1, y1, x2, y2, board, whiteMove);
      case "Knight":
        return knightValidator(x1, y1, x2, y2, board, whiteMove);
      default:
        return false;
    }   
  }
  
  /**
   * this method validates if a knight moves in a legal way.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement is taking place.

   * @param whiteMove boolean value used to determine which player is moving.

   * @return returns a boolean value determining if the knight moved in a legal way.
   */
  public boolean knightValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    if (sameTeam(x1, y1, x2, y2, board)) {
      return false;
    }   else {
      
      return Math.abs(x2 - x1)  * Math.abs(y2 - y1) == 2;
    }
    
    // add checking for captures
  }
  
  /**
   * this method validates if a pawn moves in a legal way.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement is taking place.

   * @param whiteMove boolean value used to determine which player is moving.

   * @return returns a boolean value determining if the pawn moved in a legal way.
   */
  public boolean pawnValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    
    if (sameTeam(x1, y1, x2, y2, board) || collision(x1, y1, x2, y2, board)) {
      return false;
    }   else if (otherTeam(x1, y1, x2, y2, board) && x1 == x2) {
      return false;
      // pawns can not forward capture
    }   else if (otherTeam(x1, y1, x2, y2, board) && x1 != x2 && y1 != y2) {
      return pawnCapture(x1, y1, x2, y2, whiteMove);
    }   else if (whiteMove) {
      if (y1 == 6) {
        return (y1 - y2) <= 2 && (x1 == x2) && (y1 - y2 != 0);
        // if first pawn move, can move up to 2 spaces forward
      } else {
        return (y1 - y2 == 1) && (x1 == x2);
        // if not first move, can only move 1 space forward
      }
    }   else {
      if (y1 == 1) {
        return (y2 - y1 <= 2) && (x1 == x2) && (y1 - y2 != 0);
        // if first pawn move, can move up to 2 spaces forward
      } else {
        return (y2 - y1 == 1) && (x1 == x2);
        // if not first move, can only move 1 space forward
      }
    }
    
    // add promotion logic
 
  }
  
  /**
   * this method validates if a rook moves in a legal way.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement is taking place.

   * @param whiteMove boolean value used to determine which player is moving.

   * @return returns boolean value determining if the rook moves in a legal way.
   */
  public boolean rookValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    if (sameTeam(x1, y1, x2, y2, board) || collision(x1, y1, x2, y2, board)) {
      return false;
    }   else if (x1 == x2 && y1 == y2) {
      return false;
      // piece has not moved
  } else if (x1 != x2 && y1 != y2) {
      return false;
      // only one of x or y can change at once
    }   else {
      return true;
    }
    
  }
  
  /**
   * this method validates if a bishop moves in a legal way.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement is taking place.

   * @param whiteMove boolean value used to determine which player is moving.

   * @return returns boolean value determining if the bishop moves in a legal way.
   */
  public boolean bishopValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    if (sameTeam(x1, y1, x2, y2, board) || collision(x1, y1, x2, y2, board)) {
      return false;
    }   else if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
      if (x1 == x2 && y1 == y2) {
        return false;
      } else {
        return true;
        // must move the same x amount as y amount
      }
    }   else {
      return false;
    }
    
    
  }
  
  /**
   * this method validates if a queen moves in a legal way.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement is taking place.

   * @param whiteMove boolean value used to determine which player is moving.

   * @return returns boolean value determining if the queen moves in a legal way.
   */
  public boolean queenValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    return bishopValidator(x1, y1, x2, y2, board, whiteMove) 
        ||  rookValidator(x1, y1, x2, y2, board, whiteMove);
    
  }
  
  /**
   * this method validates that a king moves in a legal way.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement is taking place.

   * @param whiteMove boolean value used to determine which player is moving.

   * @return returns boolean value determining if the king moves in a legal way.
   */
  public boolean kingValidator(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    if (sameTeam(x1, y1, x2, y2, board) || collision(x1, y1, x2, y2, board)) {
      return false;
      
    }   else if (x1 == x2 && y1 == y2) {
      return false;
      
    }   else {
      
      return Math.abs((x2 - x1) * (y2 - y1)) == 1 || Math.abs(x2 - x1) + Math.abs(y2 - y1) == 1;
      // can move to any adjacent tile 
      
    }
    
    // add check/checkmate logic to not allow certain moves
  }
  
  /**
   * this method checks if a moving piece is attempting to land on a teammate piece.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement is taking place.

   * @return returns boolean value determining if a moving pieces lands on a teammate piece.
   */
  public boolean sameTeam(int x1, int y1, int x2, int y2, Board board) {
    
    if (board.getPiece(x1, y1) != null && board.getPiece(x2, y2) != null) {
      return board.getPiece(x1, y1).isBlack() == board.getPiece(x2, y2).isBlack();
    }   else {
      return false;
    }
    
  }
  
  /**
   * this method determines if a movement contains any collisions.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement is taking place.

   * @return returns boolean value determining if a movemeent contains any collisions.
   */
  public boolean collision(int x1, int y1, int x2, int y2, Board board) {
    
    int dx = Integer.compare(x2, x1);
    int dy = Integer.compare(y2, y1);
    
    int xcurr = x1 + dx;
    int ycurr = y1 +  dy;
    
    while (xcurr != x2 || ycurr != y2) {
      
      if (board.getPiece(xcurr, ycurr) != null) {
        return true;
      }
      
      xcurr += dx;
      ycurr += dy;
    }
    return false;
  }
  
  
  /**
   * this method checks if a moving piece is attempting to land on an enemy piece.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param board the board in which this movement is taking place.

   * @return returns boolean value determining if a moving piece lands on an enemy piece.
   */
  public boolean otherTeam(int x1, int y1, int x2, int y2, Board board) {
    
    if (board.getPiece(x1, y1) != null && board.getPiece(x2, y2) != null) {
      return board.getPiece(x1, y1).isBlack() != board.getPiece(x2, y2).isBlack();
    }   else {
      return false;
    }
  }
  
  
  /**
   * this method determines if a pawn makes a legal capture.

   * @param x1 the initial x value of the movement.

   * @param y1 the initial y value of the movement.

   * @param x2 the destination x value of the movement.

   * @param y2 the destination y value of the movement.

   * @param whiteMove boolean value used to determine which player is moving.

   * @return returns boolean value determining if a pawn makes a legal capture.
   */
  public boolean pawnCapture(int x1, int y1, int x2, int y2, boolean whiteMove) {
    
    if (whiteMove) {
      
      return Math.abs(x2 - x1) == 1 && (y1 - y2) == 1;
      // pawns capture diagonally right or left 1 tile
      
    }   else {
      return Math.abs(x2 - x1) == 1 && (y2 - y1) == 1;
      // pawns capture diagonally right or left 1 tile
    }
    
  }
  
  
  public boolean isKingCheckmated(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    if (!this.isKingChecked(x1, y1, x2, y2, board, whiteMove)) {
      return false;
    }   else {
      return true;
    }
    
  }
  
  
  
  public boolean isKingChecked(int x1, int y1, int x2, int y2, Board board, boolean whiteMove) {
    
    Piece king = whiteMove ? board.getWhiteKing() : board.getBlackKing();
    assert king != null;
    
    int kingCol = king.getColumn();
    int kingRow = king.getRow();
    
    if (board.getPiece(x1, y1) != null && board.getPiece(x1, y1).getName().equals("King")) {
      kingCol = x2;
      kingRow = y2;
    }
    
    
    return hitByRook(x1, y1, x2, y2, king, kingCol, kingRow, 0, 1, board) ||
           hitByRook(x1, y1, x2, y2, king, kingCol, kingRow, 0, -1, board) ||
           hitByRook(x1, y1, x2, y2, king, kingCol, kingRow, 1, 0, board) ||
           hitByRook(x1, y1, x2, y2, king, kingCol, kingRow, -1, 0, board) ||
          
           hitByBishop(x1, y1, x2, y2, king, kingCol, kingRow, 1, 1, board) ||
           hitByBishop(x1, y1, x2, y2, king, kingCol, kingRow, 1, -1, board) ||
           hitByBishop(x1, y1, x2, y2, king, kingCol, kingRow, -1, 1, board) ||
           hitByBishop(x1, y1, x2, y2, king, kingCol, kingRow, -1, -1, board) ||
          
           hitByKnight(x2, y2, king, kingCol, kingRow, board) ||
          
           hitByPawn(x2, y2, king, kingCol, kingRow, board) ||
          
           hitByKing(king, kingCol, kingRow, board);
           
  }
  
  
  public boolean hitByRook(int x1, int y1, int col, int row, Piece king, int kingCol, int kingRow,
      int colVal, int rowVal, Board board) {
    
    for (int i = 1; i<8; i++) {
      if (kingCol + (i*colVal) == col && kingRow + (i * rowVal) == row) {
        break;
      }
      
      Piece piece = board.getPiece(kingCol + (i * colVal), kingRow + (i * rowVal));
      if (piece != null && piece != board.getPiece(x1, y1)) {
        if (piece.isBlack() != king.isBlack() && (piece.getName().equals("Rook") || piece.getName().equals("Queen"))) {
          return true;
        }
        break;
      }
      
      
    }
    
    return false;
  }
  
  
  public boolean hitByBishop(int x1, int y1, int col, int row, Piece king, int kingCol, int kingRow,
      int colVal, int rowVal, Board board) {
    
    for (int i = 1; i<8; i++) {
      if (kingCol - (i*colVal) == col && kingRow - (i * rowVal) == row) {
        break;
      }
      
      Piece piece = board.getPiece(kingCol - (i * colVal), kingRow - (i * rowVal));
      if (piece != null && piece != board.getPiece(x1, y1)) {
        if (piece.isBlack() != king.isBlack() && (piece.getName().equals("Bishop") || piece.getName().equals("Queen"))) {
          return true;
        }
        break;
      }
      
      
    }
    
    return false;
  }
  
  
  public boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow, Board board) {
    return checkKnight(board.getPiece(kingCol - 1, kingRow - 2), king, col, row) || 
           checkKnight(board.getPiece(kingCol - 2, kingRow - 1), king, col, row) ||
           checkKnight(board.getPiece(kingCol + 2, kingRow + 1), king, col, row) || 
           checkKnight(board.getPiece(kingCol + 1, kingRow + 2), king, col, row) || 
           checkKnight(board.getPiece(kingCol - 1, kingRow + 2), king, col, row) || 
           checkKnight(board.getPiece(kingCol + 1, kingRow - 2), king, col, row) || 
           checkKnight(board.getPiece(kingCol + 2, kingRow - 1), king, col, row) || 
           checkKnight(board.getPiece(kingCol - 2, kingRow + 1), king, col, row); 
  }
  
  public boolean checkKnight(Piece piece, Piece king, int col, int row) {
    return piece != null && piece.isBlack() != king.isBlack() && piece.getName().equals("Knight") &&
        !(piece.getColumn() == col && piece.getRow() == row);
    
  }
  
  
  public boolean hitByKing(Piece king, int kingCol, int kingRow, Board board) {
    
    return checkKing(board.getPiece(kingCol + 1, kingRow + 1), king) ||
           checkKing(board.getPiece(kingCol - 1, kingRow - 1), king) || 
           checkKing(board.getPiece(kingCol + 1, kingRow), king) ||
           checkKing(board.getPiece(kingCol - 1, kingRow), king) ||
           checkKing(board.getPiece(kingCol, kingRow - 1), king) ||
           checkKing(board.getPiece(kingCol, kingRow + 1), king) ||
           checkKing(board.getPiece(kingCol + 1, kingRow - 1), king) ||
           checkKing(board.getPiece(kingCol - 1, kingRow + 1), king);
  }
  
  public boolean checkKing(Piece piece, Piece king) {
    return piece != null && piece.isBlack() != king.isBlack() && piece.getName().equals("King");
  }
  
  public boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow, Board board) {
    
    int val = king.isBlack() ? 1 : -1;
    
    return checkPawn(board.getPiece(kingCol + 1, kingRow + val ), king, col, row) ||
           checkPawn(board.getPiece(kingCol - 1, kingRow + val ), king, col, row);

  }
  
  public boolean checkPawn(Piece piece, Piece king, int col, int row) {
    return piece != null && piece.isBlack() != king.isBlack() && piece.getName().equals("Pawn") &&
        !(piece.getColumn() == col && piece.getRow() == row);
  }
  

}
