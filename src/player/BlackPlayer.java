package player;

import java.util.ArrayList;
import gui.Board;
import pieces.Piece;

public class BlackPlayer extends Player{

  public BlackPlayer(Board board, String name) {
    super(board);
    
    this.name = name;
  }

  @Override
  public ArrayList<Piece> getPieces() {
    
    for(Piece piece: this.board.getBlackPieces()) {
      this.pieces.add(piece);
    }
    return this.pieces;
  }

  @Override
  public int getNumCaptured() {
    return this.numCaptured;
  }

  @Override
  public int getNumPieces() {
    return getPieces().size();
  }

  @Override
  public String getName() {
    return this.name;
  }

}
