package game;

import java.util.ArrayList;
import gui.Board;
import pieces.Piece;
import player.Player;

public class GameThread implements Runnable{
  
  private Player player1, player2;
  private Board board;
  private boolean player1turn;
  private boolean gameOver;
  private ArrayList<Piece> player1Pieces, player2Pieces;
  
  public GameThread(Player player1, Player player2, Board board) {
    this.player1 = player1;
    this.player2 = player2;
    this.board = board;
    this.player1turn = true;
    this.gameOver = false;
    this.player1Pieces = player1.getPieces();
    this.player2Pieces = player2.getPieces();
  }
  
  

  @Override
  public void run() {
    this.board.drawPieces(this.player1Pieces,this.player2Pieces);
    
  }

}
