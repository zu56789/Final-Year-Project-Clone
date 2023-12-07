package move;

/**
 * this class represents the coordinates of a movement.

 * @author Zuhayr
 *
 */
public class Move {
  
  private int x1;
  private int x2;
  private int y1;
  private int y2;
  
  /**
   * constructor for a move.

   * @param x1 initial x value of the movement.

   * @param y1 initial y value of the movement.

   * @param x2 destination x value of the movement.

   * @param y2 destintion y value of the movement.
   */
  public Move(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  /**
   * this method gets the initial x value of the movement.

   * @return returns the initial x value.
   */
  public int getx1() {
    return this.x1;
  }
  
  
  /**
   * this method gets the initial y value of the movement.

   * @return returns the initial y value.
   */
  public int gety1() {
    return this.y1;
  }
  
  /**
   * this method gets the destination x value of the movement.

   * @return returns the destination x value.
   */
  public int getx2() {
    return this.x2;
  }
  
  /**
   * this method gets the destination y value of a movement.

   * @return returns the destination y value.
   */
  public int gety2() {
    return this.y2;
  }
  
  
}
