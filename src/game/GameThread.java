package game;

import gui.Board;
import java.util.ArrayList;
import move.Move;
import movevalidator.MoveValidator;
import pieces.Piece;
import player.Player;

/**
 * this class represents each specific game of chess in the environment.

 * @author Zuhayr
 *
 */
public class GameThread implements Runnable {
  private Player player1;
  private Player player2;
  private Board board;
  private boolean player1turn;
  private boolean gameOver;
  private MoveValidator moveValidator;
  // add checking for gameOver
  
  /**
   * constructor for each game in the environment.

   * @param player1 the player with the white pieces.

   * @param player2 the player with the black pieces.
   */
  public GameThread(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
    this.board = new Board();
    this.player1turn = true;
    this.gameOver = false;
    moveValidator = MoveValidator.getInstance();
    // singleton instance of move validator
  }
  
  @Override
  public void run() {
    this.board.drawPieces(this.getPlayer1Pieces(), this.getPlayer2Pieces());
    
    while (!this.gameOver) {
      if (this.player1turn) {
        System.out.println("WHITE TURN");
        this.simulateTurn(player1turn);
        
      } else {
        System.out.println("BLACK TURN");
        this.simulateTurn(player1turn);
      }
    }
    
    
  }
  
  
  /**
   * this method simulates each turn in the game.

   * @param whiteturn boolean value used to determine who is currently moving.
   */
  public void simulateTurn(boolean whiteturn) {
    
    while (!this.getBoard().pressed()) {
      Thread.yield(); 
    }
    
    while (!this.getBoard().released()) {
      Thread.yield();
    }
     
    if (this.getBoard().pressed()) {
      
      int x1 = this.getBoard().getPressedX();
      int y1 = this.getBoard().getPressedY();
      
      Piece piece1 = this.getBoard().getPiece(x1, y1);
      
      if (this.getBoard().released()) {
        int x2 = this.getBoard().getReleasedX();
        int y2 = this.getBoard().getReleasedY();
        
        Piece piece2 = this.getBoard().getPiece(x2, y2);
        
        Move move = new Move(x1, y1, x2, y2);
        
        if (moveValidator.validMove(move, this.getBoard(), whiteturn)) {
          
          if (moveValidator.otherTeam(x1, y1, x2, y2, this.getBoard())) {
            // capture
            this.captureUpdate(piece1, piece2, x2, y2, whiteturn);
            this.board.drawPieces(this.player1.getPieces(), this.player2.getPieces());
            System.out.println("White has captured " + this.player1.getNumCaptured());
            System.out.println("Black has captured " + this.player2.getNumCaptured());
          } else {
            // non capture
            this.updateBoard(piece1, x2, y2, whiteturn);
            this.board.drawPieces(this.player1.getPieces(), this.player2.getPieces());
            
          }
          
          if (piece1.getName() == "Pawn" && move.gety2() == 0 && whiteturn) {
            System.out.println("PROMOTION!!!!!");
          }
          
          if (piece1.getName() == "Pawn" && move.gety2() == 7 && !whiteturn) {
            System.out.println("PROMOTION!!!!!");
          }
          
          if (whiteturn) {
            this.player1turn = false;
          } else {
            this.player1turn = true;
          }
        }   else {
          System.out.println("Invalid move");
          simulateTurn(whiteturn);
        }
      }

    }   else {
      
      simulateTurn(whiteturn);
    }
    
  }
  
  
  /**
   * this method updates the board after a move has been carried out.

   * @param piece1 the piece that is moving.

   * @param x2 the x value of the tile the piece is moving to.

   * @param y2 the y value of the tile the piece is moving to.

   * @param whiteturn boolean value used to determine who is currently moving.
   */
  public void updateBoard(Piece piece1, int x2, int y2, boolean whiteturn) {
    
    if (whiteturn) {
      
      ArrayList<Piece> list = this.getPlayer1Pieces();
      list.remove(piece1);
      
      piece1.setColumn(x2);
      piece1.setRow(y2);
      
      list.add(piece1);
      
      this.player1.setPieces(list);
         
    }   else {
      
      ArrayList<Piece> list = this.getPlayer2Pieces();
      list.remove(piece1);
      
      piece1.setColumn(x2);
      piece1.setRow(y2);
      
      list.add(piece1);
      
      this.player2.setPieces(list);

    }

  }
  
  
  /**
   * this method updates the board after a successful capture takes place.

   * @param piece1 the piece that is moving.

   * @param piece2 the piece that is getting captured.

   * @param x2 the x value of the destination tile.

   * @param y2 the y value of the destination tile.

   * @param whiteturn the boolean value used to determine who is currently moving.
   */
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
      
      this.player1.setNumCaptured(this.player1.getNumCaptured() + 1);
      
    }   else {
      
      ArrayList<Piece> list = this.getPlayer1Pieces();
      list.remove(piece2);
      piece2.setColumn(20);
      piece2.setRow(20);
      list.add(piece2);
      list.remove(piece2);
      this.player1.setPieces(list);
      
      this.player2.setNumCaptured(this.player2.getNumCaptured() + 1);
      
    }
       
  }
  
  /**
   * this method gets the board currently being played on.

   * @return board value used in the game.
   */
  public Board getBoard() {
    return this.board;
  }
  
  /**
   * this method gets the pieces of player one.

   * @return arraylist of the pieces held by player one.
   */
  public ArrayList<Piece> getPlayer1Pieces() {
    return this.player1.getPieces();
  }
  
  /**
   * this method gets the pieces of player two.

   * @return arraylist of the pieces held by player two.
   */
  public ArrayList<Piece> getPlayer2Pieces() {
    return this.player2.getPieces();
  }
  
}
