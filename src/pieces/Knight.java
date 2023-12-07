package pieces;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * this class represents a knight piece.

 * @author Zuhayr
 *
 */
public class Knight extends Piece {

  /**
   * constructor for knight piece.

   * @param column column in which the piece belongs to.

   * @param row row in which the piece belongs to.

   * @param isBlack boolean value determining the colour of the piece.
   */
  public Knight(int column, int row, boolean isBlack) {
    
    this.column = column;
    this.row = row;
    this.isBlack = isBlack;
    this.name = "Knight";
    this.pic = getPic();
    
  }
  
  @Override
  public Image getPic() {
    if (isBlack()) {
      try {
        return ImageIO.read(Knight.class.getResource("/resources/blackKnight.png"));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }   else {
      try {
        return ImageIO.read(Knight.class.getResource("/resources/whiteKnight.png"));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }
  }

  @Override
  public int getColumn() {
    return this.column;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public boolean isBlack() {
    return this.isBlack;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setColumn(int column) {
    this.column = column;
    
  }

  @Override
  public void setRow(int row) {
    this.row = row;
    
  }

}
