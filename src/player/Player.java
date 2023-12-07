package player;

import java.util.ArrayList;
import move.Move;
import pieces.Piece;

/**
 * this abstract class provides a blueprint for player data and functionality.

 * @author Zuhayr
 *
 */
public abstract class Player {
  
  protected ArrayList<Piece> pieces;
  protected int numCaptured;
  protected String name;
  protected int numPieces;
  
  /**
   * constructor for each player.
   */
  public Player() {
    this.numCaptured = 0;
  }
  
  /**
   * this method gets the pieces of a player.

   * @return returns arraylist of pieces.
   */
  public abstract ArrayList<Piece> getPieces();
  
  /**
   * this method gets the number of captured pieces the player has.

   * @return returns int value of the number of captured pieces.
   */
  public abstract int getNumCaptured();
  
  /**
   * this method gets the number of pieces the player has.

   * @return returns int value of the number of pieces the player has.
   */
  public abstract int getNumPieces();
  
  /**
   * this method gets the name of the player.

   * @return returns string value of the player name.
   */
  public abstract String getName();
  
  /**
   * this method creates a new move object for the player.

   * @param x1 initial x value of the movement.

   * @param y1 initial y value of the movement.

   * @param x2 destination x value of the movement.

   * @param y2 destination y value of the movement.

   * @return returns the move object created.
   */
  public abstract Move makeMove(int x1, int y1, int x2, int y2);
  
  /**
   * this method sets the number of captured pieces for the player.

   * @param numCaptured int value of the number of captured pieces.
   */
  public abstract void setNumCaptured(int numCaptured);
  
  /**
   * this method sets the number of pieces the player currently has.

   * @param numPieces int value of the number of current pieces.
   */
  public abstract void setNumPieces(int numPieces);
  
  /**
   * this method sets the list of pieces a player has.

   * @param pieces arraylist of pieces the player has.
   */
  public abstract void setPieces(ArrayList<Piece> pieces);
  
  /**
   * this method sets the initial pieces of the player.

   * @return returns arraylist of initial pieces.
   */
  protected abstract ArrayList<Piece> initialPieces();

}
