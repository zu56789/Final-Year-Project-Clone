package game;

import gui.Board;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import move.Move;
import movevalidator.MoveValidator;
import pieces.Bishop;
import pieces.Knight;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
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
  private JFrame frame;
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
    
    
    frame = new JFrame();
    
    frame.setTitle("Game Status");
    frame.setLayout(new GridBagLayout());
    frame.setSize(new Dimension(600, 400));
    frame.setLayout(new BorderLayout());
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    
    
    JLabel turnLabel = new JLabel ("White Turn", SwingConstants.CENTER);
    frame.add(turnLabel, BorderLayout.NORTH);
    
    
    
    frame.setVisible(true);
    
    this.board.drawPieces(this.getPlayer1Pieces(), this.getPlayer2Pieces());
    
    while (!this.gameOver) {
      if (this.player1turn) {
        turnLabel.setText("White Turn");
        this.simulateTurn(player1turn);
        
      } else {
        turnLabel.setText("Black Turn");
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
          
          System.out.println("Player 1 has " + this.player1.getNumPieces() + " pieces");
          System.out.println("Player 2 has " + this.player2.getNumPieces() + " pieces");
          
          if (whiteturn) {
            this.player1turn = false;
          } else {
            this.player1turn = true;
          }
        }   else {
          
          if (moveValidator.isKingChecked(x1, y1, x2, y2, board, whiteturn)) {
            System.out.println("Check");
            simulateTurn(whiteturn);
          } else {
            System.out.println("Invalid move");
            simulateTurn(whiteturn);
          }
          
          
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
      
      if (piece1.getName() == "Pawn" && y2 == 0) {
        
        System.out.println("PROMOTION!!");
        
        pawnPromotion(piece1,whiteturn);
        
      }
         
    }   else {
      
      ArrayList<Piece> list = this.getPlayer2Pieces();
      list.remove(piece1);
      
      piece1.setColumn(x2);
      piece1.setRow(y2);
      
      list.add(piece1);
      
      this.player2.setPieces(list);
      
      if (piece1.getName() == "Pawn" && y2 == 7) {
        
        System.out.println("PROMOTION!!");
        
        pawnPromotion(piece1,whiteturn);
        
      }

    }

  }
  
  
  public void pawnPromotion(Piece piece1, boolean whiteturn) {
    
    Piece newPiece;
    
    if (whiteturn) {
      
      /*Bishop whiteBishop = new Bishop(piece1.getColumn(),piece1.getRow(),false);
      Queen whiteQueen = new Queen(piece1.getColumn(),piece1.getRow(),false);
      Rook whiteRook = new Rook(piece1.getColumn(),piece1.getRow(),false);
      Knight whiteKnight = new Knight(piece1.getColumn(),piece1.getRow(),false);*/
      
      
      newPiece = new Queen(piece1.getColumn(),piece1.getRow(), false);
      ArrayList<Piece> list = this.getPlayer1Pieces();
      list.remove(piece1);
      piece1.setColumn(20);
      piece1.setRow(20);
      list.add(piece1);
      list.remove(piece1);
      list.add(newPiece);
      this.player1.setPieces(list);
      
    }
    
    else {
      
      
      /*Bishop blackBishop = new Bishop(piece1.getColumn(),piece1.getRow(),true);
      Queen blackQueen = new Queen(piece1.getColumn(),piece1.getRow(),true);
      Rook blackRook = new Rook(piece1.getColumn(),piece1.getRow(),true);
      Knight blackKnight = new Knight(piece1.getColumn(),piece1.getRow(),true);*/
      
      newPiece = new Queen(piece1.getColumn(),piece1.getRow(), true);
      
      ArrayList<Piece> list = this.getPlayer2Pieces();
      list.remove(piece1);
      piece1.setColumn(20);
      piece1.setRow(20);
      list.add(piece1);
      list.remove(piece1);
      
      list.add(newPiece);
      
      this.player2.setPieces(list);
      
    }
    
  }
  
  
  /*public Piece promotedPieceSelection(Bishop bishop, Queen queen, Rook rook, Knight knight) {
    
    
    JFrame frame1 = new JFrame();
    frame1.setTitle("Promotion Choice");
    frame1.setLayout(new GridBagLayout());
    frame1.setMinimumSize(new Dimension(534, 557));
    frame1.setLocationRelativeTo(null);
    frame1.setResizable(false);
    
    
    ImageIcon bishopImage = new ImageIcon(bishop.getPic());
    JLabel bishopLabel = new JLabel(bishopImage);
    bishopLabel.setName("Bishop");
    
    
    ImageIcon queenImage = new ImageIcon(queen.getPic());
    JLabel queenLabel = new JLabel(queenImage);
    
    ImageIcon rookImage = new ImageIcon(rook.getPic());
    JLabel rookLabel = new JLabel(rookImage);
    
    ImageIcon knightImage = new ImageIcon(knight.getPic());
    JLabel knightLabel = new JLabel(knightImage); 
    
    
    frame1.setVisible(true);
    
    return bishop;
  }*/
  
  
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
