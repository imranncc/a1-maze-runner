package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public interface Explorer {
    boolean solveMaze(int x, int y);

    List<int[]> getPath();

    String getFactorizedPath();
}
