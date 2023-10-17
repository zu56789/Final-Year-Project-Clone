package pieces;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import gui.Board;

public class Pawn extends Piece{

  public Pawn(Board board, int column, int row, boolean isBlack) {
    super(board);
    
    this.column = column;
    this.row = row;
    
    this.isBlack = isBlack;
    
    this.xPoint = column * board.tileSize;
    this.yPoint = row * board.tileSize;
    
    this.name = "Pawn";
    
    this.pic = getPic();
    
  }
  
  
  public Image getPic() {
    if (isBlack) {
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

}
