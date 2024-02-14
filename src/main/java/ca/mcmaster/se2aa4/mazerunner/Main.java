package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();

        Options options = new Options();
        options.addOption("i", "input", true, "Input file path");
        options.addOption("m", "method", true, "Method to solve the maze (righthand or tremaux)");
        options.addOption("p", "path", true, "Path to verify against the generated path");

        try {
            CommandLine line = parser.parse(options, args);

            String inputFilePath = line.getOptionValue("input");
            String method = line.hasOption("method") ? line.getOptionValue("method").toLowerCase() : "righthand";
            String pathToVerify = line.getOptionValue("path");

            if (inputFilePath == null) {
                throw new ParseException("Input file path is required.");
            }

            char[][] maze = MazeReader.readMaze(inputFilePath);
            int[] entry = MazeReader.findEntry(maze);
            int[] exit = MazeReader.findExit(maze);
            Explorer solver;

            if ("tremaux".equals(method)) {
                solver = new Tremaux(maze, entry, exit);
            } else {
                solver = new RHR(maze, entry, exit);
            }

            solver.solveMaze(entry[0], entry[1]);
            String generatedPath = solver.getFactorizedPath();
            System.out.println("Maze solved. Factorized Path: " + generatedPath);

            String outputPath = "path_output.txt";
            TXTEditor.outputSolution(maze, solver.getPath(), outputPath);
            System.out.println("Path output to file: " + outputPath);

            if (pathToVerify != null) {
                String normalizedGeneratedPath = generatedPath.trim().replaceAll("\\s+", " ");
                String normalizedPathToVerify = pathToVerify.trim().replaceAll("\\s+", " ");

                if (normalizedGeneratedPath.equalsIgnoreCase(normalizedPathToVerify)) {
                    System.out.println("Provided path matches the generated path.");
                } else {
                    System.out.println("Provided path does not match the generated path. Provided: '" + normalizedPathToVerify + "', Generated: '" + normalizedGeneratedPath + "'");
                }
            } else {
                System.out.println("No path provided for verification.");
            }


        } catch (ParseException exp) {
            System.err.println("Parsing failed. Reason: " + exp.getMessage());
        } catch (IOException e) {
            System.err.println("IO Exception: " + e.getMessage());
        }
    }
}
