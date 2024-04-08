package game;

import gui.Board;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
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
  private JLabel whitePieceCountLabel;
  private JLabel blackPieceCountLabel;
  private JTextArea whiteMovesArea;
  private JTextArea blackMovesArea;
  // add checking for gameOver
  
  /**
   * constructor for each game in the environment.

   * @param player1 the player with the white pieces.

   * @param player2 the player with the black pieces.
   */
  public GameThread(Player player1, Player player2, MoveValidator moveValidator) {
    this.player1 = player1;
    this.player2 = player2;
    this.board = new Board();
    this.player1turn = true;
    this.gameOver = false;
    this.moveValidator = moveValidator;
    // singleton instance of move validator
  }
  
  @Override
  public void run() {
    
    
    frame = new JFrame();
    
    frame.setTitle(this.player1.getName().toUpperCase() + " vs " + this.player2.getName().toUpperCase() + " Game Status");
    frame.setLayout(new GridBagLayout());
    frame.setSize(new Dimension(600, 400));
    frame.setLayout(new BorderLayout());
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.getContentPane().setBackground(new Color(160, 178, 250));
    
    
    JLabel turnLabel = new JLabel (this.player1.getName().toUpperCase() + " turn", SwingConstants.CENTER);
    turnLabel.setForeground(Color.WHITE);
    
    frame.add(turnLabel, BorderLayout.NORTH);
    
    
    JPanel countsPanel = new JPanel(new GridLayout(1, 2));
    countsPanel.setOpaque(false);
    
    whitePieceCountLabel = new JLabel(this.player1.getName().toUpperCase() + " Pieces: 16", SwingConstants.CENTER);
    whitePieceCountLabel.setForeground(Color.WHITE);
    
    TitledBorder whitePiecesBorder = BorderFactory.createTitledBorder(this.player1.getName().toUpperCase() + " Count");
    whitePiecesBorder.setTitleColor(Color.WHITE);
    whitePieceCountLabel.setBorder(whitePiecesBorder);
    
    countsPanel.add(whitePieceCountLabel);

    blackPieceCountLabel = new JLabel(this.player2.getName().toUpperCase() + " Pieces: 16", SwingConstants.CENTER);
    blackPieceCountLabel.setBorder(BorderFactory.createTitledBorder(this.player2.getName().toUpperCase() + " Count"));
    blackPieceCountLabel.setForeground(Color.BLACK);
    
    countsPanel.add(blackPieceCountLabel);
    frame.add(countsPanel, BorderLayout.SOUTH);
    
    JPanel movesPanel = new JPanel(new GridLayout(1, 2));
    
    movesPanel.setOpaque(false);
    
    whiteMovesArea = new JTextArea();
    whiteMovesArea.setEditable(false);
    
    JScrollPane whiteScroll = new JScrollPane(whiteMovesArea);
    whiteScroll.setOpaque(false);
    
    TitledBorder whiteBorder = BorderFactory.createTitledBorder(this.player1.getName().toUpperCase() + " Moves");
    
    whiteBorder.setTitleColor(new Color(255,255,255));
    
    whiteScroll.setBorder(whiteBorder);
    movesPanel.add(whiteScroll);
     
    blackMovesArea = new JTextArea();
    blackMovesArea.setEditable(false);
    
    JScrollPane blackScroll = new JScrollPane(blackMovesArea);
    blackScroll.setOpaque(false);
    blackScroll.setBorder(BorderFactory.createTitledBorder(this.player2.getName().toUpperCase() + " Moves"));
    
    movesPanel.add(blackScroll);

    frame.add(movesPanel, BorderLayout.CENTER);
    
    
    frame.setVisible(true);
    
    this.board.drawPieces(this.getPlayer1Pieces(), this.getPlayer2Pieces());
    
    while (!this.gameOver) {
      if (this.player1turn) {
        turnLabel.setText(this.player1.getName().toUpperCase() + " turn");
        turnLabel.setForeground(Color.WHITE);
        this.simulateTurn(player1turn);
        
      } else {
        turnLabel.setText(this.player2.getName().toUpperCase() + " turn");
        turnLabel.setForeground(Color.BLACK);
        this.simulateTurn(player1turn);
      }
    }
    
    if (this.player1turn) {
      turnLabel.setText(this.player2.getName().toUpperCase() + " WINS");
      turnLabel.setForeground(Color.BLACK);
      frame.setTitle(this.player2.getName().toUpperCase() + " WINS");
    }   else {
      turnLabel.setText(this.player1.getName().toUpperCase() + " WINS");
      turnLabel.setForeground(Color.WHITE);
      frame.setTitle(this.player1.getName().toUpperCase() + " WINS");
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
          
          
          if (whiteturn) {
            whiteMovesArea.append(piece1.getName() + " from (" + piece1.getColumn() + "," + piece1.getRow() +
            ") to (" + x2 + "," + y2 + ")\n");
          } else {
            blackMovesArea.append(piece1.getName() + " from (" + piece1.getColumn() + "," + piece1.getRow() +
                ") to (" + x2 + "," + y2 + ")\n");
          }
          
          if (moveValidator.otherTeam(x1, y1, x2, y2, this.getBoard())) {
            // capture
            this.captureUpdate(piece1, piece2, x2, y2, whiteturn);
            this.board.drawPieces(this.player1.getPieces(), this.player2.getPieces());
          } else {
            // non capture
            this.updateBoard(piece1, x2, y2, whiteturn);
            this.board.drawPieces(this.player1.getPieces(), this.player2.getPieces());
            
          }
          
          if (whiteturn) {
            this.player1turn = false;
          } else {
            this.player1turn = true;
          }
        }   else {
          
          if (moveValidator.isKingCheckmated(x1, y1, x2, y2, board, whiteturn)) {
            JOptionPane.showMessageDialog(frame,"Checkmate" + "", " ",JOptionPane.ERROR_MESSAGE);
            
            if (whiteturn) {
              JOptionPane.showMessageDialog(frame, this.player2.getName() + " won" + "", " ",JOptionPane.ERROR_MESSAGE);
            }   else {
              JOptionPane.showMessageDialog(frame, this.player1.getName() + " won" + "", " ",JOptionPane.ERROR_MESSAGE);
            }
            
            this.gameOver = true;
            
          }   else {
            JOptionPane.showMessageDialog(frame,"Invalid Move" + "", " ",JOptionPane.ERROR_MESSAGE);
            simulateTurn(whiteturn);
            
          }
          
        }
      }

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
        
        pawnPromotion(piece1,whiteturn);
        
      }

    }

  }
  
  
  public void pawnPromotion(Piece piece1, boolean whiteturn) {
    
    Piece newPiece;
    
    String[] options = {"Queen", "Rook", "Bishop", "Knight"};
    
    if (whiteturn) {

      int choice = JOptionPane.showOptionDialog(null,
          "Choose an option:", 
          "Option Chooser",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE,
          null,
          options,
          options[0]);
      
      switch(choice) {
        case 0:
          newPiece = new Queen(piece1.getColumn(),piece1.getRow(), false);
          break;
        case 1:
          newPiece = new Rook(piece1.getColumn(),piece1.getRow(), false);
          break;
        case 2:
          newPiece = new Bishop(piece1.getColumn(),piece1.getRow(), false);
          break;
        case 3:
          newPiece = new Knight(piece1.getColumn(),piece1.getRow(), false);
          break;
        default:
          newPiece = new Queen(piece1.getColumn(),piece1.getRow(), false);
          break;
      }
      
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
      
      int choice = JOptionPane.showOptionDialog(null, 
          "Choose an option:", 
          "Option Chooser", 
          JOptionPane.DEFAULT_OPTION, 
          JOptionPane.INFORMATION_MESSAGE, 
          null, 
          options, 
          options[0]);
      
      switch(choice) {
        case 0:
          newPiece = new Queen(piece1.getColumn(),piece1.getRow(), true);
          break;
        case 1:
          newPiece = new Rook(piece1.getColumn(),piece1.getRow(), true);
          break;
        case 2:
          newPiece = new Bishop(piece1.getColumn(),piece1.getRow(), true);
          break;
        case 3:
          newPiece = new Knight(piece1.getColumn(),piece1.getRow(), true);
          break;
        default:
          newPiece = new Queen(piece1.getColumn(),piece1.getRow(), true);
          break;
      }
      
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
    
    whitePieceCountLabel.setText(this.player1.getName().toUpperCase() + " Pieces: " + this.player1.getNumPieces());
    blackPieceCountLabel.setText(this.player2.getName().toUpperCase() + " Pieces: " + this.player2.getNumPieces());
       
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
