package pieces;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import gui.Board;

public class Bishop extends Piece{

  public Bishop(Board board, int column, int row, boolean isBlack) {
    super(board);
    
    this.column = column;
    this.row = row;
    
    this.isBlack = isBlack;
    
    this.xPoint = column * board.tileSize;
    this.yPoint = row * board.tileSize;
    
    this.name = "Bishop";
    
    this.pic = getPic();
    
  }
  
  
  public Image getPic() {
    if (isBlack) {
      try {
        return ImageIO.read(Bishop.class.getResource("/resources/blackBishop.png"));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }
    else {
      try {
        return ImageIO.read(Bishop.class.getResource("/resources/whiteBishop.png"));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }
  }

}
