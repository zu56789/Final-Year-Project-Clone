package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import gui.Board;
import pieces.Piece;
import player.BlackPlayer;

public class TestPlayers {
  
  private BlackPlayer blackPlayer;
  private Board board = new Board();

  @Before
  public void setup() {
    blackPlayer = new BlackPlayer(board, "black"); // test a black player can be created
  }
  
  @Test
  public void testPiecesMatch() {
    
    String pieces1 = "";
    String pieces2 = "";
    
    for (Piece piece: blackPlayer.getPieces()) {
      pieces1 += piece.name;
    }
    
    for (Piece piece: new BlackPlayer(new Board(),"black").getPieces()) {
      pieces2 += piece.name;
    }
    
    assertEquals(pieces1, pieces2); // test initial pieces of two black players match
  }
  
  @Test
  public void testName() {
    assertEquals(blackPlayer.getName(), new BlackPlayer(new Board(),"black").getName());
  }

}
