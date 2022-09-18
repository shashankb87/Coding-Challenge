public class SudokuSolver {    
  public static void main(String[] args) {
    int[][] board = {
        {7, 0, 2, 0, 5, 0, 6, 0, 0},
        {0, 0, 0, 0, 0, 3, 0, 0, 0},
        {1, 0, 0, 0, 0, 9, 5, 0, 0},
        {8, 0, 0, 0, 0, 0, 0, 9, 0},
        {0, 4, 3, 0, 0, 0, 7, 5, 0},
        {0, 9, 0, 0, 0, 0, 0, 0, 8},
        {0, 0, 9, 7, 0, 0, 0, 0, 5},
        {0, 0, 0, 2, 0, 0, 0, 0, 0},
        {0, 0, 7, 0, 4, 0, 2, 0, 3} 
    };
    printBoard(board);
    if (solve(board)) {
      System.out.println("Solved successfully!");
    }
    else {
      System.out.println("Unsolvable board :(");
    }
    printBoard(board);
  }
  
  private static void printBoard(int[][] board) {
    for (int row = 0; row < 9; row++) {
      if (row % 3 == 0 && row != 0) {
        System.out.println("-----------");
      }
      for (int column = 0; column < 9; column++) {
        if (column % 3 == 0 && column != 0) {
          System.out.print("|");
        }
        System.out.print(board[row][column]);
      }
      System.out.println();
    }
  }

  private static boolean isValid(int[][] board, int num, int r, int c) {
    return !checkrow(board, num, r) && !checkCol(board, num, c) && !checkGrid(board, num, r, c);
  }

  private static boolean checkrow(int[][] board, int num, int r) {
    for (int i = 0; i < 9; i++) {
      if (board[r][i] == num) 
        return true;
    }
    return false;
  }
  
  private static boolean checkCol(int[][] board, int num, int c) {
    for (int i = 0; i < 9; i++) {
      if (board[i][c] == num) 
        return true;
    }
    return false;
  }
  
  private static boolean checkGrid(int[][] board, int num, int r, int c) {
    int currRow = r - (r % 3);
    int currCol = c - (c % 3);
    
    for (int i = currRow; i < currRow + 3; i++) {
      for (int j = currCol; j < currCol + 3; j++) {
        if (board[i][j] == num) 
          return true;
      }
    }
    return false;
  }
  
  private static boolean solve(int[][] board) {
    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        if (board[r][c] == 0) {
          for (int i = 1; i <= 9; i++) {
            if (isValid(board, i, r, c)) {
              board[r][c] = i;
              if (solve(board))
                return true;
              else 
                board[r][c] = 0;
            }
          }
          return false;
        }
      }
    }
    return true;
  } 
}
