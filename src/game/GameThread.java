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
  private int count;
  
  public GameThread(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
    this.board = new Board();
    this.player1turn = true;
    this.gameOver = false;
    this.count = 0;
  }
  
  @Override
  public void run() {
    this.board.drawPieces(this.getPlayer1Pieces(),this.getPlayer2Pieces());
    
    while(!this.gameOver) {
      
      if (this.player1turn) {
        this.simulatep1Move();
      }
      else {
        this.simulatep2Move();
      }
  
    }
    
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
  
  public void simulatep1Move() {
    
    while (!this.board.Pressed()) {
      Thread.yield();
    }
    
    while (this.board.getSelectedPiece().isBlack()) {
      Thread.yield();
    }
    
    
    
    while (!this.board.Released()) {
      Thread.yield();
    }
    
    count++;
    System.out.println(this.board.getSelectedPiece().getName() + " " + this.board.getSelectedPiece().isBlack());
    
    if (this.board.getReleasedX() < 8 && this.board.getReleasedY() < 8) {
      Piece pressed = this.board.getSelectedPiece();
      int col = this.board.getReleasedX();
      int row = this.board.getReleasedY();
      ArrayList<Piece> temp = this.getPlayer1Pieces();
      temp.remove(pressed);
      pressed.setColumn(col);
      pressed.setRow(row);
      this.player1.setPieces(temp);
      this.board.drawPieces(this.player1.getPieces(), this.player2.getPieces());
    }
    
    this.player1turn = false;
  }
  
  public void simulatep2Move() {
    
    while (!this.board.Pressed()) {
      Thread.yield();
    }
    
    while (!this.board.getSelectedPiece().isBlack()) {
      Thread.yield();
    }
    
    
    
    while (!this.board.Released()) {
      Thread.yield();
    }
    
    count++;
    System.out.println(this.board.getSelectedPiece().getName() + " " + this.board.getSelectedPiece().isBlack());
    
    if (this.board.getReleasedX() < 8 && this.board.getReleasedY() < 8) {
      Piece pressed = this.board.getSelectedPiece();
      int col = this.board.getReleasedX();
      int row = this.board.getReleasedY();
      ArrayList<Piece> temp = this.getPlayer2Pieces();
      temp.remove(pressed);
      pressed.setColumn(col);
      pressed.setRow(row);
      this.player2.setPieces(temp);
      this.board.drawPieces(this.player1.getPieces(), this.player2.getPieces());
    }
    
    this.player1turn = true;
  }
  
}
