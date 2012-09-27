
import java.util.HashMap;

public class YourEvaluator extends Evaluator {
  public static double[][] PAWN_SQUARE = {
  {0,  0,  0,  0,  0,  0,  0,  0},
  {50, 50, 50, 50, 50, 50, 50, 50},
  {10, 10, 20, 30, 30, 20, 10, 10},
  {5,  5, 10, 25, 25, 10,  5,  5},
  {0,  0,  0, 20, 20,  0,  0,  0},
  {5, -5,-10,  0,  0,-10, -5,  5},
  {5, 10, 10,-20,-20, 10, 10,  5},
  {0,  0,  0,  0,  0,  0,  0,  0}
  };
  
  public static double[][] KNIGHT_SQUARE = {
  {-50,-40,-30,-30,-30,-30,-40,-50},
  {-40,-20,  0,  0,  0,  0,-20,-40},
  {-30,  0, 10, 15, 15, 10,  0,-30},
  {-30,  5, 15, 20, 20, 15,  5,-30},
  {-30,  0, 15, 20, 20, 15,  0,-30},
  {-30,  5, 10, 15, 15, 10,  5,-30},
  {-40,-20,  0,  5,  5,  0,-20,-40},
  {-50,-40,-30,-30,-30,-30,-40,-50}
  };
  
  public static double[][] BISHOP_SQUARE = {
  {-20,-10,-10,-10,-10,-10,-10,-20},
  {-10,  0,  0,  0,  0,  0,  0,-10},
  {-10,  0,  5, 10, 10,  5,  0,-10},
  {-10,  5,  5, 10, 10,  5,  5,-10},
  {-10,  0, 10, 10, 10, 10,  0,-10},
  {-10, 10, 10, 10, 10, 10, 10,-10},
  {-10,  5,  0,  0,  0,  0,  5,-10},
  {-20,-10,-10,-10,-10,-10,-10,-20}
  };
  
  public static double[][] ROOK_SQUARE = {
  {0,  0,  0,  0,  0,  0,  0,  0},
  {5, 10, 10, 10, 10, 10, 10,  5},
  {-5,  0,  0,  0,  0,  0,  0, -5},
  {-5,  0,  0,  0,  0,  0,  0, -5},
  {-5,  0,  0,  0,  0,  0,  0, -5},
  {-5,  0,  0,  0,  0,  0,  0, -5},
  {-5,  0,  0,  0,  0,  0,  0, -5},
  {0,  0,  0,  5,  5,  0,  0,  0}
  };
  
  public static double[][] QUEEN_SQUARE = {
  {-20,-10,-10, -5, -5,-10,-10,-20},
  {-10,  0,  0,  0,  0,  0,  0,-10},
  {-10,  0,  5,  5,  5,  5,  0,-10},
  {-5,  0,  5,  5,  5,  5,  0, -5},
  {0,  0,  5,  5,  5,  5,  0, -5},
  {-10,  5,  5,  5,  5,  5,  0,-10},
  {-10,  0,  5,  0,  0,  0,  0,-10},
  {-20,-10,-10, -5, -5,-10,-10,-20}
  };
  
  public static double[][] KING_MIDDLE_SQUARE = {
  {-30,-40,-40,-50,-50,-40,-40,-30},
  {-30,-40,-40,-50,-50,-40,-40,-30},
  {-30,-40,-40,-50,-50,-40,-40,-30},
  {-30,-40,-40,-50,-50,-40,-40,-30},
  {-20,-30,-30,-40,-40,-30,-30,-20},
  {-10,-20,-20,-20,-20,-20,-20,-10},
  {20, 20,  0,  0,  0,  0, 20, 20},
  {20, 30, 10,  0,  0, 10, 30, 20}
  };
  
  public static double[][] KING_END_SQUARE = {
  {-50,-40,-30,-20,-20,-30,-40,-50},
  {-30,-20,-10,  0,  0,-10,-20,-30},
  {-30,-10, 20, 30, 30, 20,-10,-30},
  {-30,-10, 30, 40, 40, 30,-10,-30},
  {-30,-10, 30, 40, 40, 30,-10,-30},
  {-30,-10, 20, 30, 30, 20,-10,-30},
  {-30,-30,  0,  0,  0,  0,-30,-30},
  {-50,-30,-30,-30,-30,-30,-30,-50}
  };
  
  public double eval(Position p) {
    double ret = 0;
    
    // Loop doing simplified material balance
    for (int x = 0; x < p.board.length; ++x) {
      for (int y = 0; y < p.board[x].length; ++y) {
        if (p.board[x][y] == Position.Empty) {
          continue;
        }
        if (p.board[x][y] == Position.WKing) {
          ret += 32767; // material balance factor
          ret += KING_MIDDLE_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.WQueen) {
          ret += 975; // material balance factor
          ret += QUEEN_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.WRook) {
          ret += 500; // material balance factor
          ret += ROOK_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.WBishop) {
          ret += 325; // material balance factor
          ret += BISHOP_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.WKnight) {
          ret += 320; // material balance factor
          ret += KNIGHT_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.WPawn) {
          ret += 100; // material balance factor
          ret += PAWN_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.BKing) {
          ret -= 32767; // material balance factor
          ret += -1 * KING_MIDDLE_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.BQueen) {
          ret -= 975; // material balance factor
          ret += -1 * QUEEN_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.BRook) {
          ret -= 500; // material balance factor
          ret += -1 * ROOK_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.BBishop) {
          ret -= 325; // material balance factor
          ret += -1 * BISHOP_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.BKnight) {
          ret -= 320; // material balance factor
          ret += -1 * KNIGHT_SQUARE[x][y];
        }
        if (p.board[x][y] == Position.BPawn) {
          ret -= 100; // material balance factor
          ret += -1 * PAWN_SQUARE[x][y];
        }
        
      }
    }
    return ret;
  }
}
