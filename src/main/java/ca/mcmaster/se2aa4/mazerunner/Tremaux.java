package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Tremaux implements Explorer {
    private char[][] maze;
    private boolean[][] visited;
    private List<int[]> path = new ArrayList<>();
    private int[] entry;
    private int[] exit;

    public String getFactorizedPath() {
        return FactorizedPath.factorizePath(this.getPath());
    }

    public Tremaux(char[][] maze, int[] entry, int[] exit) {
        this.maze = maze;
        this.entry = entry;
        this.exit = exit;
        this.visited = new boolean[maze.length][maze[0].length];
    }

    @Override
    public boolean solveMaze(int x, int y) {

        if (x == exit[0] && y == exit[1]) {
            path.add(new int[]{x, y});
            return true;
        }

        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == '#' || visited[x][y]) {
            return false;
        }

        visited[x][y] = true;
        path.add(new int[]{x, y});

        if (solveMaze(x + 1, y) || solveMaze(x - 1, y) || solveMaze(x, y + 1) || solveMaze(x, y - 1)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    // took forever to get running all bcuz I forgot to add this in it... whomp whomp
    @Override
    public List<int[]> getPath() {
        return path;
    }
}
