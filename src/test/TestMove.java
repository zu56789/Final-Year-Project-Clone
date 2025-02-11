package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import move.Move;

public class TestMove {
  
  private Move move;

  @Before
  public void setup() {
    move = new Move(0,0,1,1);
  }
  
  @Test
  public void testGetx1() {
    assertEquals(move.getx1(),0); // test if correct x1 returned
  }
  
  @Test
  public void testGety1() {
    assertEquals(move.gety1(),0); // test if correct y1 returned
  }
  
  @Test
  public void testGetx2() {
    assertEquals(move.getx2(),1); // test if correct x2 returned
  }
  
  @Test
  public void testGety2() {
    assertEquals(move.gety2(),1); // test if correct y2 returned
  }

}
