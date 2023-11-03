// 8.12 Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board
// so that none of them share the same row, column, or diagonal. In this case, "diagonal" means all
// diagonals, not just the two that bisect the board.

public class EightQueens {

    public static void solveEightQueens() {
        int boardSize = 8;
        int[] queens = new int[boardSize];
        placeQueens(queens, 0);
    }

    public static void placeQueens(int[] queens, int row) {
        int boardSize = queens.length;

        if (row == boardSize) {
            printSolution(queens);
            return;
        }

        for (int col = 0; col < boardSize; col++) {
            if (isSafe(queens, row, col)) {
                queens[row] = col;
                placeQueens(queens, row + 1);
            }
        }
    }

    public static boolean isSafe(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col ||           // Check if same column is occupied
                    queens[i] - i == col - row || // Check if diagonal '\' is occupied
                    queens[i] + i == col + row)   // Check if diagonal '/' is occupied
            {
                return false;
            }
        }
        return true;
    }

    public static void printSolution(int[] queens) {
        int boardSize = queens.length;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (queens[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    public static void main(String[] args) {
        solveEightQueens();
    }
}
