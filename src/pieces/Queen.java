package pieces;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * this class represents a queen piece.

 * @author Zuhayr
 *
 */
public class Queen extends Piece {

  /**
   * constructor for queen piece.

   * @param column column in which the piece belongs to.

   * @param row row in which the piece belongs to.

   * @param isBlack boolean value determining the colour of the piece.
   */
  public Queen(int column, int row, boolean isBlack) {
    
    this.column = column;
    this.row = row;
    this.isBlack = isBlack;
    this.name = "Queen";
    this.pic = getPic();
    
  }
  
  @Override
  public Image getPic() {
    if (isBlack()) {
      try {
        return ImageIO.read(Queen.class.getResource("/resources/blackQueen.png"));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }   else {
      try {
        return ImageIO.read(Queen.class.getResource("/resources/whiteQueen.png"));
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
