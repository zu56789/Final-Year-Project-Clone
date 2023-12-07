package pieces;

import java.awt.Image;

/**
 * this abstract class provides a blueprint for piece data and functionality.

 * @author Zuhayr
 *
 */
public abstract class Piece {
  
  protected String name;
  protected int column;
  protected int row;
  protected boolean isBlack;
  
  protected Image pic;
  
  /**
   * this method gets the column of the piece.

   * @return returns the int column value.
   */
  public abstract int getColumn();
  
  /**
   * this method gets the row of the piece.

   * @return returns the int row value.
   */
  public abstract int getRow();
  
  /**
   * this method gets the boolean value used to determine the piece colour.

   * @return returns the boolean value used to determine the piece colour.
   */
  public abstract boolean isBlack();
  
  /**
   * this method gets the image sprite of a particular piece.

   * @return image value of the piece sprite.
   */
  public abstract Image getPic();
  
  /**
   * this method gets the name of a piece.

   * @return string value of the name of the piece.
   */
  public abstract String getName();
  
  /**
   * this method sets the column of a piece.

   * @param column new column value of the piece.
   */
  public abstract void setColumn(int column);
  
  /**
   * this method sets the row of a piece.

   * @param row new row value of the piece.
   */
  public abstract void setRow(int row);

}
