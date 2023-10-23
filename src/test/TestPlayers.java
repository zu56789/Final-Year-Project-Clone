package test;

import static org.junit.Assert.*;
import org.junit.Test;
import gui.Board;
import player.BlackPlayer;

public class TestPlayers {

  @Test
  public void setup() {
    BlackPlayer blackPlayer = new BlackPlayer(new Board(), "black"); // test a black player can be created
  }

}
