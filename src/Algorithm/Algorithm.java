/**
 * @author Risandu Disanayake
 * @author 20220177 w1953141
 */

package Algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Algorithm {

    // Method to find the shortest path in the maze
    public ArrayList<int[]> findShortestPath(int[][] maze, int[] start, int[] end) {
        // Get the number of rows and columns in the maze
        int rows = maze.length;
        int cols = maze[0].length;

        // Initialize a boolean array to keep track of visited cells
        boolean[][] visited = new boolean[rows][cols];

        // Create a priority queue to prioritize shorter distances
        Queue<Coordinate> queue = new PriorityQueue<>();
        // Add the starting point to the queue
        ArrayList<int[]> startPath = new ArrayList<>();
        startPath.add(start);
        queue.offer(new Coordinate(start[0], start[1], 0, startPath));

        // Define corresponding moves(UP, DOWN, LEFT, RIGHT)
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            // Get the current coordinate from the queue
            Coordinate current = queue.poll();
            // Check if the current coordinate is the destination
            if (current.row == end[0] && current.column == end[1]) {
                return current.path;
            }

            // Explore all possible moves
            for (int i = 0; i < moves.length; i++) {
                // Initialize new row, column, and distance for the new cell
                int newRow = current.row;
                int newCol = current.column;
                int distance = current.distanceFromStart;

                // Explore the direction until hitting a rock, wall(maze boundary) or the endpoint
                while (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                        && maze[newRow][newCol] == 0 && (newRow != end[0] || newCol != end[1])) {
                    newRow += moves[i][0];
                    newCol += moves[i][1];
                    distance += 1;
                }

                // Roll back one step if the end is not reached
                if (newRow != end[0] || newCol != end[1]) {
                    newRow -= moves[i][0];
                    newCol -= moves[i][1];
                    distance -= 1;
                }

                // Check if the new coordinate is not visited
                if (!visited[newRow][newCol]) {
                    // Mark the new coordinate as visited
                    visited[newRow][newCol] = true;
                    // Construct the new path
                    ArrayList<int[]> newPath = new ArrayList<>(current.path);
                    newPath.add(new int[]{newRow, newCol});
                    // Add the new coordinate with the shorter path to the queue
                    queue.offer(new Coordinate(newRow, newCol, distance, newPath));
                }
            }
        }

        // Return a message if no path is found
        return new ArrayList<>();
    }
}
