package test;

import static org.junit.Assert.*;
import org.junit.Test;
import game.GameThread;
import gui.Board;
import player.*;

public class TestGameThread {

  @Test
  public void test() {
    GameThread game = new GameThread(new WhitePlayer("white"), new BlackPlayer("black"), new Board());
    Thread gameThread = new Thread(game);
    gameThread.start();
    
  }

}
