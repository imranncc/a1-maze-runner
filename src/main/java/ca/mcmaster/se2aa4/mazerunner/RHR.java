package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class RHR implements Explorer {
    private char[][] maze;
    private List<int[]> path;
    private boolean[][] visited;
    private int[] exit;

    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public String getFactorizedPath() {
        return FactorizedPath.factorizePath(this.getPath());
    }

    public RHR(char[][] maze, int[] entry, int[] exit) {
        this.maze = maze;
        this.path = new ArrayList<>();
        this.visited = new boolean[maze.length][maze[0].length];
        this.exit = exit;
        solveMaze(entry[0], entry[1], 1);
    }

    private boolean solveMaze(int x, int y, int dir) {
        if (x == exit[0] && y == exit[1]) {
            path.add(new int[]{x, y});
            return true;
        }

        if (!isValidStep(x, y)) {
            return false;
        }

        visited[x][y] = true;
        path.add(new int[]{x, y});

        for (int i = 0; i < DIRECTIONS.length; i++) {
            int newDir = (dir + i) % DIRECTIONS.length;
            int[] nextStep = DIRECTIONS[newDir];
            if (solveMaze(x + nextStep[0], y + nextStep[1], newDir)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private boolean isValidStep(int x, int y) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == ' ' && !visited[x][y];
    }

    @Override
    public boolean solveMaze(int x, int y) {
        return false;
    }

    public List<int[]> getPath() {
        return path;
    }
}
