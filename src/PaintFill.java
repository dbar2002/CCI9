// 8.10 Paint Fill: Implement the "paint fill" function that one might see on many image editing programs.
// That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color,
// fill in the surrounding area until the color changes from the original color.

public class PaintFill {

    public static void paintFill(char[][] screen, int x, int y, char newColor) {
        char originalColor = screen[y][x];
        if (originalColor == newColor) {
            return; // Nothing to do if the new color is the same as the original color.
        }
        paintFillHelper(screen, x, y, originalColor, newColor);
    }

    private static void paintFillHelper(char[][] screen, int x, int y, char originalColor, char newColor) {
        if (x < 0 || x >= screen[0].length || y < 0 || y >= screen.length || screen[y][x] != originalColor) {
            return; // Base case: out of bounds or different color.
        }

        // Fill the current cell with the new color.
        screen[y][x] = newColor;

        // Recursively fill adjacent cells.
        paintFillHelper(screen, x - 1, y, originalColor, newColor); // Left
        paintFillHelper(screen, x + 1, y, originalColor, newColor); // Right
        paintFillHelper(screen, x, y - 1, originalColor, newColor); // Up
        paintFillHelper(screen, x, y + 1, originalColor, newColor); // Down
    }

    public static void main(String[] args) {
        char[][] screen = {
                {'R', 'R', 'R', 'G', 'G'},
                {'R', 'R', 'G', 'G', 'G'},
                {'G', 'G', 'G', 'R', 'R'},
                {'R', 'R', 'R', 'R', 'R'}
        };

        int x = 2; // X-coordinate of the starting point
        int y = 2; // Y-coordinate of the starting point

        char newColor = 'B'; // New color to fill

        paintFill(screen, x, y, newColor);

        // Print the updated screen
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++) {
                System.out.print(screen[i][j] + " ");
            }
            System.out.println();
        }
    }
}
