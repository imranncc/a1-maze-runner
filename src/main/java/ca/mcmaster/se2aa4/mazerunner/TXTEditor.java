package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TXTEditor {
    public static void outputSolution(char[][] maze, List<int[]> path, String outputPath) throws IOException {
        markPath(maze, path);
        String factorizedPath = FactorizedPath.factorizePath(path);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (char[] row : maze) {
                writer.write(new String(row));
                writer.newLine();
            }
            writer.newLine();
            writer.write(factorizedPath);
        }
    }
    private static void markPath(char[][] maze, List<int[]> path) {
        for (int i = 0; i < path.size() - 1; i++) {
            int[] current = path.get(i);
            int[] next = path.get(i + 1);

            if (current[0] == next[0]) {
                maze[current[0]][Math.min(current[1], next[1])] = current[1] < next[1] ? '>' : '<';
            } else {
                maze[Math.min(current[0], next[0])][current[1]] = current[0] < next[0] ? 'v' : '^';
            }
        }
    }
}

