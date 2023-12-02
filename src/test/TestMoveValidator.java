package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import gui.Board;
import move.Move;
import moveValidator.MoveValidator;
import player.BlackPlayer;
import player.WhitePlayer;
import pieces.*;

public class TestMoveValidator {
  
  private MoveValidator moveValidator;
  private Board board;
  private WhitePlayer player1;
  private BlackPlayer player2;
  private ArrayList<Piece> list1;
  private ArrayList<Piece> list2;

  @Before
  public void setup() {
    moveValidator = MoveValidator.getInstance(); 
    board = new Board();
    player1 = new WhitePlayer("player1");
    player2 = new BlackPlayer("player2");
    list1 = new ArrayList<>();
    list2 = new ArrayList<>();
    //board.drawPieces(player1.getPieces(), player2.getPieces());
  }
  
  @Test
  public void testHighCoordinate() {
    
    
    Move move1 = new Move(10,10,2,3);
    assertEquals(moveValidator.validMove(move1, board, false), false);
    // tests that coordinates above 7 make a move invalid
  }
  
  @Test
  public void testWrongTeam() {
    
    list2.add(new Rook(0,0,true));
    board.drawPieces(list1, list2);
    
    Move move2 = new Move(0,0,4,4); // black piece
    assertEquals(moveValidator.validMove(move2, board, true), false);
    // tests that trying to move the other teams piece makes move invalid
    
  }
  
  @Test
  public void testKnight() {
    
    list2.add(new Knight(1,0,true));
    board.drawPieces(list1, list2);
    
    Move move3 = new Move(1,0,2,2);
    assertEquals(moveValidator.validMove(move3, board, false),true);
    // tests that a knight is able to move in its specified way
  }
  
  @Test
  public void testPawn() {
    
    list1.add(new Pawn(1,6,false));
    board.drawPieces(list1, list2);
    
    Move move4 = new Move(1,6,1,4);
    assertEquals(moveValidator.validMove(move4, board, true),true);
    // tests that a pawn is able to move in its specified way
  }
  
  @Test
  public void testRook() {
    
    list2.add(new Rook(0,0,true));
    board.drawPieces(list1, list2);
    
    
    Move move5 = new Move(0,0,0,4);
    
    assertEquals(moveValidator.validMove(move5, board, false),true);
    // tests that a rook is able to move in its speicified way 
    
  }
  
  @Test
  public void testBishop() {
    
    list1.add(new Bishop(2,7,false));
    board.drawPieces(list1, list2);
    
    Move move6 = new Move(2,7,4,5);
    assertEquals(moveValidator.validMove(move6, board, true),true);
    // tests that a bishop is able to move in its speicified way 
  }
  
  @Test
  public void testQueen() {
    
    list2.add(new Queen(3,0,true));
    board.drawPieces(list1, list2);
    
    Move move7 = new Move(3,0,7,4);
    assertEquals(moveValidator.validMove(move7, board, false),true);
    // tests that a queen is able to move in its speicified way 
  }
  
  @Test
  public void testKing() {
    
    list1.add(new King(4,7,false));
    board.drawPieces(list1, list2);
    
    Move move8 = new Move(4,7,3,6);
    assertEquals(moveValidator.validMove(move8, board, true),true);
    // tests that a queen is able to move in its speicified way 
  }
  
  @Test
  public void testCollision() {
    
    board.drawPieces(player1.getPieces(), player2.getPieces());
    
    Move move9 = new Move(0,7,0,5);
    assertEquals(moveValidator.validMove(move9, board, true),false);
    // tests that a collision is detected and makes a move invalid
    
  }
  
  @Test
  public void testSameTeam() {
    
    list1.add(new Knight(1,7,false));
    list1.add(new Pawn(0,5,false));
    
    board.drawPieces(list1, list2);
    
    Move Move10 = new Move(1,7,0,5);
    assertEquals(moveValidator.validMove(Move10, board, true),false);
    // tests that a piece can not be moved to a tile with another same colour piece on
    
  }
  
  @Test
  public void testCapture() {
    
    list1.add(new Knight(1,7,false));
    list2.add(new Queen(0,5,true));
    
    board.drawPieces(list1, list2);
    
    Move move11 = new Move(1,7,0,5);
    assertEquals(moveValidator.validMove(move11, board, true),true);
    // tests that a legal capture is accepted
    
  }
  
  @Test
  public void testPawnCapture() {
    list1.add(new Pawn(1,6,false));
    list2.add(new Pawn(2,5,true));
    
    board.drawPieces(list1, list2);
    
    Move move12 = new Move(1,6,2,5);
    assertEquals(moveValidator.validMove(move12, board, true),true);
    // tests that a pawn can capture a piece either front right or front left from it
    
  }
  
  @Test
  public void testPawnBlocked() {
    list1.add(new Pawn(1,6,false));
    list2.add(new Pawn(1,5,true));
    
    board.drawPieces(list1, list2);
    
    Move move13 = new Move(1,6,1,5);
    assertEquals(moveValidator.validMove(move13, board, true),false);
    // tests that a pawn can not move forward if there is an opponent piece blocking it
    
  }
  

}
