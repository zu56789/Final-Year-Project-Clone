package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import gui.Board;
import move.Move;
import moveValidator.MoveValidator;

public class TestMoveValidator {
  
  private MoveValidator moveValidator;
  private Board board;

  @Before
  public void setup() {
    moveValidator = MoveValidator.getInstance(); // test if instance of moveValidator can be created.
    board = new Board();
  }
  
  @Test
  public void testHighCoordinate() {
    
    Move move1 = new Move(10,10,2,3);
    assertEquals(moveValidator.validMove(move1, board, false), false);
  }

}
