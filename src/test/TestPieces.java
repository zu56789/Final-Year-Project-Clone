package test;

import static org.junit.Assert.*;
import org.junit.Test;
import pieces.*;

public class TestPieces {

  
  @Test
  public void testPawn() {
    Pawn pawn = new Pawn(1, 0, true);
    assertEquals(pawn.getName(), new Pawn(1,1, true).getName()); // tests if pawns have same name.
  }
  
  @Test
  public void testBishop() {
    Bishop bishop = new Bishop(1, 0, true);
    assertEquals(bishop.getName(), new Bishop(1,1, true).getName()); // tests if bishops have same name.
  }
  
  @Test
  public void testQueen() {
    Queen queen = new Queen(1, 0, true);
    assertEquals(queen.getName(), new Queen(1,1, true).getName()); // tests if queens have same name.
  }
  
  @Test
  public void testKing() {
    King king = new King(1, 0, true);
    assertEquals(king.getName(), new King(1,1, true).getName()); // tests if kings have same name.
  }
  
  @Test
  public void testRook() {
    Rook rook = new Rook(1, 0, true);
    assertEquals(rook.getName(), new Rook(1,1, true).getName()); // tests if rooks have same name.
  }
  
  @Test
  public void testKnight() {
    Knight knight = new Knight(1, 0, true);
    assertEquals(knight.getName(), new Knight(1,1, true).getName()); // tests if knights have same name.
  }
  
  @Test
  public void testSameTeam() {
    Bishop bishop = new Bishop(1, 0, true);
    Queen queen = new Queen(1, 0, true);
    assertEquals(bishop.isBlack(), queen.isBlack()); // tests if pieces of the same team show as being on the same team.
  }

  @Test
  public void testSprites() {
    King king = new King(1, 0, true);
    King king2 = new King(1, 0, false);
    assertNotEquals(king.getPic(), king2.getPic()); // tests identical pieces of different teams create different sprites.
  }
  
  @Test 
  public void testSetRow() {
    King king = new King(1,0,true);
    king.setRow(4);
    assertEquals(king.getRow(),4); // tests setting row of a piece 
  }
  
  @Test
  public void testSetColumn() {
    Bishop bishop = new Bishop(1,0,false);
    bishop.setColumn(3);
    assertEquals(bishop.getColumn(), 3); // tests setting column of a piece
  }

  
  
}
