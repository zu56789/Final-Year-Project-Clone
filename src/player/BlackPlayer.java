package player;

import java.util.ArrayList;
import gui.Board;
import pieces.Piece;

public class BlackPlayer extends Player{

  public BlackPlayer(Board board, String colour) {
    super(board);
    
    this.colour = colour;
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
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getNumPieces() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getColour() {
    // TODO Auto-generated method stub
    return null;
  }

}
