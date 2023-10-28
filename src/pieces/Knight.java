package pieces;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Knight extends Piece{

  public Knight(int column, int row, boolean isBlack) {
    
    this.column = column;
    this.row = row;
    this.isBlack = isBlack;
    this.name = "Knight";
    this.pic = getPic();
    
  }
  
  public Image getPic() {
    if (isBlack()) {
      try {
        return ImageIO.read(Knight.class.getResource("/resources/blackKnight.png"));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }
    else {
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
