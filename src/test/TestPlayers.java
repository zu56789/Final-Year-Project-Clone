package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pieces.Piece;
import player.BlackPlayer;

public class TestPlayers {
  
  private BlackPlayer blackPlayer;
  
  @Before
  public void setup() {
    blackPlayer = new BlackPlayer("black");
  }
  
  @Test
  public void testPiecesMatch() {
    
    String pieces1 = "";
    String pieces2 = "";
    
    for (Piece piece: blackPlayer.getPieces()) {
      pieces1 += piece.getName();
    }
    
    for (Piece piece: new BlackPlayer("black").getPieces()) {
      pieces2 += piece.getName();
    }
    
    assertEquals(pieces1, pieces2); // test initial pieces of two black players match
  }
  
  @Test
  public void testName() {
    assertEquals(blackPlayer.getName(), new BlackPlayer("black").getName());
  }
  
  @Test
  public void testNumCaptured() {
    assertEquals(blackPlayer.getNumCaptured(), 0); // initial number of captures should be zero
  }
  
  @Test
  public void testNumPieces() {
    assertEquals(blackPlayer.getNumPieces(), 16); // initial number of pieces should be sixteen
  }
  

}
