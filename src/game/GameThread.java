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
  
  public GameThread(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
    this.board = new Board();
    this.player1turn = true;
    this.gameOver = false;
    this.player1Pieces = this.getPlayer1Pieces();
    this.player2Pieces = this.getPlayer2Pieces();
  }
  
  

  @Override
  public void run() {
    this.board.drawPieces(this.player1Pieces,this.player2Pieces);
    
  }
  
  
  public Board getBoard() {
    return this.board;
  }
  
  public ArrayList<Piece> getPlayer1Pieces() {
    return this.player1.getPieces();
  }
  
  public ArrayList<Piece> getPlayer2Pieces() {
    return this.player2.getPieces();
  }
  
  

}
