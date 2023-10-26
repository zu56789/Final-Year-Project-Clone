package move;

public class Move {
  
  private int x1, y1, x2, y2;
  
  public Move(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  public int getx1() {
    return this.x1;
  }
  
  
  public int gety1() {
    return this.y1;
  }
  
  public int getx2() {
    return this.x2;
  }
  
  public int gety2() {
    return this.y2;
  }
  
  
}
