package player;

import java.util.ArrayList;
import pieces.Piece;

public abstract class Player {
  
  protected ArrayList<Piece> pieces;
  protected int numCaptured;
  protected String name;
  protected int numPieces;
  
  public Player() {
    this.numCaptured = 0;
  }
  
  public abstract ArrayList<Piece> getPieces();
  public abstract int getNumCaptured();
  public abstract int getNumPieces();
  public abstract String getName();
  public abstract void makeMove(); // Create Move class and implement logic 
  public abstract void setNumCaptured(int numCaptured);
  public abstract void setNumPieces(int numPieces);
  public abstract void setPieces(ArrayList<Piece> pieces);
  protected abstract ArrayList<Piece> initialPieces();

}
