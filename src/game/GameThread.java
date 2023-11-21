package game;

import java.util.ArrayList;
import gui.Board;
import move.Move;
import moveValidator.MoveValidator;
import pieces.Piece;
import player.Player;

public class GameThread implements Runnable{
  
  private Player player1, player2;
  private Board board;
  private boolean player1turn;
  private boolean gameOver;
  private int count;
  private MoveValidator moveValidator;
  
  public GameThread(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
    this.board = new Board();
    this.player1turn = true;
    this.gameOver = false;
    this.count = 0;
    moveValidator = MoveValidator.getInstance();
  }
  
  @Override
  public void run() {
    this.board.drawPieces(this.getPlayer1Pieces(),this.getPlayer2Pieces());
    
    while(!this.gameOver) {
      if (this.player1turn) {
        System.out.println("WHITE TURN");
        this.simulateTurn(player1turn);
        
      }
      else {
        System.out.println("BLACK TURN");
        this.simulateTurn(player1turn);
      }
    }
    
    
  }
  
  
  public void simulateTurn(boolean whiteturn) {
    
    while (!this.getBoard().Pressed()) {
      Thread.yield();
    }
    
    while (!this.getBoard().Released()) {
      Thread.yield();
    }
    
    if (this.getBoard().Pressed()) {
      
      int x1 = this.getBoard().getPressedX();
      int y1 = this.getBoard().getPressedY();
      
      Piece piece1 = this.getBoard().getPiece(x1, y1);
      
      
      
      if (this.getBoard().Released()) {
        int x2 = this.getBoard().getReleasedX();
        int y2 = this.getBoard().getReleasedY();
        
        Piece piece2 = this.getBoard().getPiece(x2, y2);
        // work on capture stuff later
        
        
        Move move = new Move(x1,y1,x2,y2);
        
        if (moveValidator.validMove(move, this.getBoard(), whiteturn)) {
          
          if (moveValidator.otherTeam(x1, y1, x2, y2, this.getBoard())) {
            this.captureUpdate(piece1, piece2, x2, y2, whiteturn);
            this.board.drawPieces(this.player1.getPieces(), this.player2.getPieces());
            System.out.println("White has captured " + this.player1.getNumCaptured());
            System.out.println("Black has captured " + this.player2.getNumCaptured());
          }
          
          else {
            this.updateBoard(piece1, x2, y2, whiteturn);
            this.board.drawPieces(this.player1.getPieces(), this.player2.getPieces());
            
          }
          
          if (whiteturn) {
            this.player1turn = false;
          }
          else {
            this.player1turn = true;
          }
        }
        
        else {
          System.out.println("Invalid move");
          simulateTurn(whiteturn);
        }
      }
      
      
      
    }
    
    else {
      
      simulateTurn(whiteturn);
    }
    
    
    
  }
  
  
  public void updateBoard(Piece piece1, int x2, int y2, boolean whiteturn) {
    
    if (whiteturn) {
      
      ArrayList<Piece> list = this.getPlayer1Pieces();
      list.remove(piece1);
      
      piece1.setColumn(x2);
      piece1.setRow(y2);
      
      list.add(piece1);
      
      this.player1.setPieces(list);
      
      
    }
    
    else {
      
      ArrayList<Piece> list = this.getPlayer2Pieces();
      list.remove(piece1);
      
      piece1.setColumn(x2);
      piece1.setRow(y2);
      
      list.add(piece1);
      
      this.player2.setPieces(list);

    }

  }
  
  
  public void captureUpdate(Piece piece1, Piece piece2, int x2, int y2, boolean whiteturn) {
    
    this.updateBoard(piece1, x2, y2, whiteturn);

    if (whiteturn) {
      
      ArrayList<Piece> list = this.getPlayer2Pieces();
      list.remove(piece2);
      piece2.setColumn(20);
      piece2.setRow(20);
      list.add(piece2);
      list.remove(piece2);
      this.player2.setPieces(list);
      
      this.player1.setNumCaptured(this.player1.getNumCaptured()+1);
      
      
    }
    
    else {
      
      ArrayList<Piece> list = this.getPlayer1Pieces();
      list.remove(piece2);
      piece2.setColumn(20);
      piece2.setRow(20);
      list.add(piece2);
      list.remove(piece2);
      this.player1.setPieces(list);
      
      this.player2.setNumCaptured(this.player2.getNumCaptured()+1);
      
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
  
}
