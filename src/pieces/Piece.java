package pieces;

import java.awt.Graphics;
import java.awt.Image;
import gui.Board;

public class Piece {
  
  public String name;
  public int column, row, xPoint, yPoint;
  public boolean isBlack;
  
  public Image pic;
  
  Board board;
  
  public Piece(Board board) {
    this.board = board;
  }
  
  public void paint(Graphics g) {
    g.drawImage(pic,xPoint,yPoint,null);
  }
  

}
