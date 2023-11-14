package pieces;

import java.awt.Image;

public abstract class Piece {
  
  protected String name;
  protected int column, row;
  protected boolean isBlack;
  
  protected Image pic;
  
  public abstract int getColumn();
  public abstract int getRow();
  public abstract boolean isBlack();
  public abstract Image getPic();
  public abstract String getName();
  public abstract void setColumn(int column);
  public abstract void setRow(int row);

}
