package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import game.GameThread;
import gui.Board;
import pieces.Piece;
import player.*;

public class TestGameThread {
  
  private GameThread game;
  private GameThread game2;

  @Before
  public void setup() {
    
    game = new GameThread(new WhitePlayer("white"), new BlackPlayer("black"));
    Thread gameThread = new Thread(game);
    gameThread.start();
    
    game2 = new GameThread(new WhitePlayer("a"), new BlackPlayer("b"));
    Thread gameThread2 = new Thread(game2);
    gameThread2.start();
    
  }
  
  @Test 
  public void testinItialp1Pieces() {
    
    String pieces1 = "";
    String pieces2 = "";
    
    for (Piece piece: game.getPlayer1Pieces()) {
      
      pieces1 = piece.getName() + piece.getColumn() + piece.getRow() + piece.isBlack();
    }
    
    for (Piece piece: game2.getPlayer1Pieces()) {
      
      pieces2 = piece.getName() + piece.getColumn() + piece.getRow() + piece.isBlack();
    }
     
    assertEquals(pieces1, pieces2); // tests player1 pieces are the same in different threads
  }
  
  @Test
  public void testInitialp2Pieces() {
    
    String pieces1 = "";
    String pieces2 = "";
    
    for (Piece piece: game.getPlayer2Pieces()) {
      
      pieces1 = piece.getName() + piece.getColumn() + piece.getRow() + piece.isBlack();
    }
    
    for (Piece piece: game2.getPlayer2Pieces()) {
      
      pieces2 = piece.getName() + piece.getColumn() + piece.getRow() + piece.isBlack();
    }
     
    assertEquals(pieces1, pieces2); // tests player2 pieces are the same in different threads
    
  }
  
  @Test
  public void testBoards() {
    assertNotEquals(game.getBoard(), game2.getBoard()); // tests different threads create different boards
  }
  

}
