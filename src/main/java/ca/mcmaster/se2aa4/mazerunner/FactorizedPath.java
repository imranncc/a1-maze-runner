package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class FactorizedPath {
    public static String factorizePath(List<int[]> path) {
        if (path == null || path.size() < 2) {
            return "";
        }

        StringBuilder factorizedPath = new StringBuilder();
        int orientation = 0;
        int[] start = path.get(0);
        int[] next = path.get(1);
        int dx = next[0] - start[0];
        int dy = next[1] - start[1];

        if (dx == 1) orientation = 2;
        else if (dx == -1) orientation = 0;
        else if (dy == 1) orientation = 1;
        else if (dy == -1) orientation = 3;

        char direction = 'F';
        int count = 1;

        for (int i = 2; i < path.size(); i++) {
            int[] prev = path.get(i - 1);
            int[] curr = path.get(i);

            dx = curr[0] - prev[0];
            dy = curr[1] - prev[1];

            int newOrientation = -1;
            if (dx == 1) newOrientation = 2;
            else if (dx == -1) newOrientation = 0;
            else if (dy == 1) newOrientation = 1;
            else if (dy == -1) newOrientation = 3;

            if (newOrientation != orientation) {
                if (count > 0 && direction == 'F') {
                    factorizedPath.append(count).append('F').append(' ');
                    count = 0;
                }

                if ((orientation == 0 && newOrientation == 1) ||
                        (orientation == 1 && newOrientation == 2) ||
                        (orientation == 2 && newOrientation == 3) ||
                        (orientation == 3 && newOrientation == 0)) {
                    factorizedPath.append('R').append(' ');
                } else {
                    factorizedPath.append('L').append(' ');
                }

                orientation = newOrientation;
                direction = 'F';
            }
            count++;
        }

        if (count > 0 && direction == 'F') {
            factorizedPath.append(count).append('F');
        }

        return factorizedPath.toString().trim();
    }
}
