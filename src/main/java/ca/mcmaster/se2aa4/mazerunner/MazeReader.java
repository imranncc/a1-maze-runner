package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeReader {
    // takes the given txt maze file and converted into a 2D array
    public static char[][] readMaze(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        char[][] maze = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            maze[i] = lines.get(i).toCharArray();
        }
        return maze;
    }

    // for mvp bare minimum we need a method for finding the entry/exit point of the maze
    // so I can create an algorithm that can solve the maze
    public static int[] findEntry(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == ' ') {
                return new int[]{i, 0};
            }
        }
        return new int[]{-1, -1};
    }
    public static int[] findExit(char[][] maze) {
        int lastCol = maze[0].length - 1;
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][lastCol] == ' ') {
                return new int[]{i, lastCol};
            }
        }
        return new int[]{-1, -1};
    }
}

