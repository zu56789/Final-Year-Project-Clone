package test;

import static org.junit.Assert.*;
import org.junit.Test;
import move.Move;

public class TestMove {
  
  private Move move;

  @Test
  public void test() {
    move = new Move(0,0,1,1);
  }

}
