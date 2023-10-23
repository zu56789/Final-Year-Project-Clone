package player;

import java.util.ArrayList;
import gui.Board;
import pieces.Piece;

public abstract class Player {
  
  protected final Board board;
  protected ArrayList<Piece> pieces;
  protected int numCaptured;
  protected String colour;
  
  public Player(Board board) {
    this.board = board;
    this.pieces = new ArrayList<>();
    this.numCaptured = 0;
  }
  
  public abstract ArrayList<Piece> getPieces();
  public abstract int getNumCaptured();
  public abstract int getNumPieces();
  public abstract String getColour();

}
