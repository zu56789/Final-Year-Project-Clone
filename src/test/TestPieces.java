package test;

import static org.junit.Assert.*;
import org.junit.Test;
import pieces.*;
import gui.Board;



public class TestPieces {

  
  @Test
  public void testPawn() {
    Pawn pawn = new Pawn(new Board(), 1, 0, true);
    assertEquals(pawn.name, (new Pawn(new Board(), 2,0, true)).name); // test if pawns are created properly.
  }
  
  @Test
  public void testRook() {
    Rook rook = new Rook(new Board(), 1, 0, true);
    assertEquals(rook.name, (new Rook(new Board(), 2,0, true)).name); // test if rooks are created properly.
  }
  
  @Test
  public void testKnight() {
    Knight knight = new Knight(new Board(), 1, 0, true);
    assertEquals(knight.name, (new Knight(new Board(), 1,0, true)).name); // test if knights are created properly.
  }
  
  @Test
  public void testKing() {
    King king = new King(new Board(), 1, 0, true);
    assertEquals(king.name, (new King(new Board(), 1, 0, true)).name); // test if kings are created properly.
  }
  
  @Test
  public void testQueen() {
    Queen queen = new Queen(new Board(), 1, 0, true);
    assertEquals(queen.name, (new Queen(new Board(), 1, 0, true)).name); // test if queens are created properly.
  }
  
  @Test
  public void testBishop() {
    Bishop bishop = new Bishop(new Board(), 1, 0, true);
    assertEquals(bishop.name, (new Bishop(new Board(), 1, 0, true)).name); // test if bishops are created properly.
  }
  
  
  
  
  
  

}
