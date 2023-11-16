package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import gui.Board;
import move.Move;
import moveValidator.MoveValidator;
import player.BlackPlayer;
import player.WhitePlayer;

public class TestMoveValidator {
  
  private MoveValidator moveValidator;
  private Board board;
  private WhitePlayer player1;
  private BlackPlayer player2;

  @Before
  public void setup() {
    moveValidator = MoveValidator.getInstance(); 
    board = new Board();
    player1 = new WhitePlayer("player1");
    player2 = new BlackPlayer("player2");
    board.drawPieces(player1.getPieces(), player2.getPieces());
  }
  
  @Test
  public void testHighCoordinate() {
    
    Move move1 = new Move(10,10,2,3);
    assertEquals(moveValidator.validMove(move1, board, false), false);
    // tests that coordinates above 7 make a move invalid
  }
  
  @Test
  public void testWrongTeam() {
    Move move2 = new Move(0,0,4,4); // black piece
    assertEquals(moveValidator.validMove(move2, board, true), false);
    // tests that trying to move the other teams piece makes move invalid
    
  }
  
  @Test
  public void knightTest() {
    Move move3 = new Move(1,0,2,2);
    assertEquals(moveValidator.validMove(move3, board, false),true);
    // tests that a knight is able to move in its specified way
  }
  
  @Test
  public void pawnTest() {
    Move move4 = new Move(1,6,1,4);
    assertEquals(moveValidator.validMove(move4, board, true),true);
    // tests that a pawn is able to move in its specified way
  }
  
  @Test
  public void rookTest() {
    Move move5 = new Move(0,0,0,3);
    assertEquals(moveValidator.validMove(move5, board, false),true);
    // tests that a rook is able to move in its speicified way 
    
  }
  
  @Test
  public void testBishop() {
    Move move6 = new Move(2,7,4,5);
    assertEquals(moveValidator.validMove(move6, board, true),true);
    // tests that a bishop is able to move in its speicified way 
  }
  

}
