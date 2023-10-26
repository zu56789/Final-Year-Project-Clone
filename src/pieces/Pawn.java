package pieces;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Pawn extends Piece{

  public Pawn(int column, int row, boolean isBlack) {
    
    this.column = column;
    this.row = row;
    this.isBlack = isBlack;
    this.name = "Pawn";
    this.pic = getPic();
    
  }
  
  
  public Image getPic() {
    if (isBlack()) {
      try {
        return ImageIO.read(Pawn.class.getResource("/resources/blackPawn.png"));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }
    else {
      try {
        return ImageIO.read(Pawn.class.getResource("/resources/whitePawn.png"));
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

}
