package Implementation_BruteForce;

import java.util.*;

public class MinimumPeopleRequired {

    private static final int[] rowDirs = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    private static final int[] colDirs = {0, 0, -1, 1};
    private static final int EMPTY = 0;
    private static final int WALL = 1;

    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 1},
                {0, 0, 0}
        };
        System.out.println(minPeopleRequired(grid)); // Output should be 2
    }

    public static int minPeopleRequired(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int peopleCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == EMPTY && !visited[i][j]) {
                    // Start a new DFS for each new group of `0`s
                    dfs(grid, visited, i, j);
                    peopleCount++;
                }
            }
        }

        return peopleCount;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int row, int col) {
        int n = grid.length;
        int m = grid[0].length;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{row, col});
        visited[row][col] = true;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int r = current[0];
            int c = current[1];

            for (int i = 0; i < 4; i++) {
                int newRow = r + rowDirs[i];
                int newCol = c + colDirs[i];

                if (isValid(newRow, newCol, n, m) && grid[newRow][newCol] == EMPTY && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    stack.push(new int[]{newRow, newCol});
                }
            }
        }
    }

    private static boolean isValid(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
}
