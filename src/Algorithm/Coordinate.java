/**
 * @author Risandu Disanayake
 * @author 20220177 w1953141
 */

package Algorithm;

import java.util.ArrayList;

// class to represent coordinates(each cell)
class Coordinate implements Comparable<Coordinate> {
    int row;
    int column;
    int distanceFromStart;
    ArrayList<int[]> path;  // ArrayList to store coordinates

    // Constructor to initialize coordinates
    Coordinate(int row, int column, int distanceFromStart, ArrayList<int[]> path) {
        this.row = row;
        this.column = column;
        this.distanceFromStart = distanceFromStart;
        this.path = path;
    }

    // Method to display coordinates
    @Override
    public String toString() {
        return "Total distance: " + distanceFromStart + " | Path: " + path;
    }

    @Override
    public int compareTo(Coordinate other) {
        // Prioritize coordinates with shorter distances
        return Integer.compare(this.distanceFromStart, other.distanceFromStart);
    }
}