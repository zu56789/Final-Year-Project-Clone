package player;

import java.util.ArrayList;
import move.Move;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 * this class represnts the player with the white pieces.

 * @author Zuhayr
 *
 */
public class WhitePlayer extends Player {
  
  
  /**
   * constructor for a white player.

   * @param name string value of the name of the player.
   */
  public WhitePlayer(String name) {
    
    this.name = name;
    this.pieces = this.initialPieces();
  }

  @Override
  public ArrayList<Piece> getPieces() {
    return this.pieces;
  }

  @Override
  public int getNumCaptured() {
    return this.numCaptured;
  }

  @Override
  public int getNumPieces() {
    return this.pieces.size();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setNumCaptured(int numCaptured) {
    this.numCaptured = numCaptured;
    
  }

  @Override
  public void setNumPieces(int numPieces) {
    this.numPieces = numPieces;
    
  }

  @Override
  public void setPieces(ArrayList<Piece> pieces) {
    this.pieces = pieces;
    
  }

  @Override
  protected ArrayList<Piece> initialPieces() {
    
    this.pieces = new ArrayList<>();
    
    this.pieces.add(new Knight(1, 7, false));
    this.pieces.add(new Knight(6, 7, false));
    
    this.pieces.add(new Pawn(0, 6, false));
    this.pieces.add(new Pawn(1, 6, false));
    this.pieces.add(new Pawn(2, 6, false));
    this.pieces.add(new Pawn(3, 6, false));
    this.pieces.add(new Pawn(4, 6, false));
    this.pieces.add(new Pawn(5, 6, false));
    this.pieces.add(new Pawn(6, 6, false));
    this.pieces.add(new Pawn(7, 6, false));
    
    this.pieces.add(new Rook(0, 7, false));
    this.pieces.add(new Rook(7, 7, false));
    
    this.pieces.add(new Bishop(2, 7, false));
    this.pieces.add(new Bishop(5, 7, false));
    
    this.pieces.add(new Queen(3, 7, false));
    
    this.pieces.add(new King(4, 7, false));
    
    return this.pieces;
    // pieces at the start of the game
    
  }

  @Override
  public Move makeMove(int x1, int y1, int x2, int y2) {
    return new Move(x1, y1, x2, y2);
  }

}
