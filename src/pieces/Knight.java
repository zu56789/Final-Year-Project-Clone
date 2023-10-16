package pieces;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import gui.Board;

public class Knight extends Piece{

  public Knight(Board board, int column, int row, boolean isBlack) {
    super(board);
    this.column = column;
    this.row = row;
    
    this.isBlack = isBlack;
    
    this.xPoint = column * board.tileSize;
    this.yPoint = row * board.tileSize;
    
    this.name = "Knight";
    
    //File srcFile = new File("C:\\Users\\zuzu5\\eclipse-workspace\\zkac412cbProject\\src\\resources\blackKnight.png");
    //this.pic = ImageIO.read(srcFile);
    
    this.pic = getPic();
    
    
  }
  
  public Image getPic() {
    if (isBlack) {
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

}
