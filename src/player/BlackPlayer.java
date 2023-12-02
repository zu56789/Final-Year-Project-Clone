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

public class BlackPlayer extends Player{

  public BlackPlayer(String name) {
    
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
    
    this.pieces.add(new Knight(1, 0, true));
    this.pieces.add(new Knight(6, 0, true));
    
    this.pieces.add(new Pawn(0, 1, true));
    this.pieces.add(new Pawn(1, 1, true));
    this.pieces.add(new Pawn(2, 1, true));
    this.pieces.add(new Pawn(3, 1, true));
    this.pieces.add(new Pawn(4, 1, true));
    this.pieces.add(new Pawn(5, 1, true));
    this.pieces.add(new Pawn(6, 1, true));
    this.pieces.add(new Pawn(7, 1, true));
    
    this.pieces.add(new Rook(0, 0, true));
    this.pieces.add(new Rook(7, 0, true));
    
    this.pieces.add(new Bishop(2, 0, true));
    this.pieces.add(new Bishop(5, 0, true));
    
    this.pieces.add(new Queen(3, 0, true));
    
    this.pieces.add(new King(4, 0, true));
    
    return this.pieces;
    // pieces when the game first starts
    
  }

  @Override
  public Move makeMove(int x1, int y1, int x2, int y2) {
    return new Move(x1, y1, x2, y2);
  }

}
