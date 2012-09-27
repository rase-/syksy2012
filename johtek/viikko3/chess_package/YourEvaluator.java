
import java.util.HashMap;

public class YourEvaluator extends Evaluator {
  
  
  
  public double materialBalance(Position p) {
    // Loop doing simplified material balance
    for (int x = 0; x < p.board.length; ++x) {
      for (int y = 0; y < p.board[x].length; ++y) {
        if (p.board[x][y] == Position.Empty) {
          continue;
        }
        if (p.board[x][y] == Position.WKing) {
          ret += 32767;
        }
        if (p.board[x][y] == Position.WQueen) {
          ret += 975;
        }
        if (p.board[x][y] == Position.WRook) {
          ret += 500;
        }
        if (p.board[x][y] == Position.WBishop) {
          ret += 325;
        }
        if (p.board[x][y] == Position.WKnight) {
          ret += 320;
        }
        if (p.board[x][y] == Position.WPawn) {
          ret += 100;
        }
        if (p.board[x][y] == Position.BKing) {
          ret -= 32767;
        }
        if (p.board[x][y] == Position.BQueen) {
          ret -= 975;
        }
        if (p.board[x][y] == Position.BRook) {
          ret -= 500;
        }
        if (p.board[x][y] == Position.BBishop) {
          ret -= 325;
        }
        if (p.board[x][y] == Position.BKnight) {
          ret -= 320;
        }
        if (p.board[x][y] == Position.BPawn) {
          ret -= 100;
        }
        
      }
    }
    return ret;
  }
  
  public double eval(Position p) {
    double ret = 0;
    
    ret += materialBalance(p);
    
    
    return ret;
  }
}
