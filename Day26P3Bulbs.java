import java.util.*;

public class Day26P3Bulbs {
    private static int matrixScore2(int[][] grid) {
        // M-2
        // T.C:- O(M*N)
        // S.C:- O(1)

        int n = grid.length;
        int m = grid[0].length;

        // 1) We already know we anyhow have to convert all the first col elemetns to 1
        // , so directly initilize
        // ans with it's contribution
        int score = n * (1 << m - 1 - 0); // for column 0 (j = 0)

        // now starting flipping bits from col1
        for (int j = 1; j < m; j++) {
            int onesCnt = 0;
            for (int i = 0; i < n; i++) {
                // If first element of this row was 0 , it means this should be flipped and
                // added to onesCnt ,
                // else directly add it
                onesCnt += (grid[i][0] == 0) ? (grid[i][j] ^ 1) : grid[i][j];
            }

            score += Math.max(onesCnt, n - onesCnt) * (1 << m - 1 - j);
            // if onesCnt is more then it is multiplying factor , if there are more 0s then
            // we need to flip all them hence
            // take maximum of both and multiply with 2^(m - 1 -j)
        }

        return score;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] grid = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        System.out.print(matrixScore2(grid));

        sc.close();
    }

    private static int matrixScore1(int[][] grid) {
        // M-1 ,
        // T.C:- O(M*N)
        // S.C:- O(M*N)

        int n = grid.length;
        int m = grid[0].length;

        // let's assume it is a n x m matrix
        int score = 0;
        // 1) Make col0 all elements as 1 to maximize the score
        for (int i = 0; i < n; i++) {
            // if first element of any row is one then only flip
            if (grid[i][0] == 0) {

                for (int j = 0; j < m; j++) {

                    // flip this to 1
                    grid[i][j] ^= 1; // by taking xor with 1 we can flip the bits

                }
            }
        }

        // 2) Now our aim is to maximize the 1s count in the grid , start from col1
        for (int j = 1; j < m; j++) {
            int onesCnt = 0;
            for (int i = 0; i < n; i++) {
                onesCnt += grid[i][j];
            }

            if (onesCnt < n - onesCnt) {
                // if noOf 1s < noOf 0s then flip all the bits in this column
                for (int i = 0; i < n; i++) {
                    grid[i][j] ^= 1; // flip all the bits in this column to maximize the score
                }
            }
        }

        // 3) Now calc the score
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                score += grid[i][j] * (1 << (m - 1 - j)); // same as (bit val * 2^m-1-j)
            }
        }

        return score;
    }
}