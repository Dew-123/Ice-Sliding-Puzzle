/**
 * @author Risandu Disanayake
 * @author 20220177 w1953141
 */

package Algorithm;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Instantiate Parser
        Parser parser = new Parser();

        try {
            // Read file
            parser.readFile();
            // Load lines
            parser.loadLines();

            // Load values into maze
            parser.loadValues();

            // Get maze
            int[][] maze = parser.getMaze();

            // Get start and end points
            int[] startPoint = parser.getBeginPoint();
            int[] endPoint = parser.getEndPoint();
            System.out.println("Start point: " + "("+ (startPoint[1]+1) + ", "+ (startPoint[0]+1)+ ")");
            System.out.println("End point: " +"("+ (endPoint[1]+1) + ", "+(endPoint[0]+1)+ ")");

            // Instantiate Algorithm and find shortest path
            Algorithm algorithm = new Algorithm();

            long start = System.currentTimeMillis();

            ArrayList<int[]> shortestPath = algorithm.findShortestPath(maze, startPoint, endPoint);

            System.out.println("Shortest Path: ");
            if (shortestPath.isEmpty()) {
                System.out.println("No path found!");
            } else {
                displayPath(shortestPath);
            }
            long now = System.currentTimeMillis();
            double elapsed = now - start;
            System.out.println("Elapsed time = " + elapsed + " milliseconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayPath(ArrayList<int[]> shortestPath) {
        if (shortestPath.size() > 0) {
            System.out.printf("1. Start at (%d, %d)\n", shortestPath.get(0)[1] + 1, shortestPath.get(0)[0] + 1);
            for (int i = 1; i < shortestPath.size(); i++) {
                int[] currentCell = shortestPath.get(i);
                int[] previousCell = shortestPath.get(i - 1);
                int dx = currentCell[0] - previousCell[0];
                int dy = currentCell[1] - previousCell[1];
                String direction = "";

                if (dx < 0) {
                    direction = "Up";
                } else if (dx > 0) {
                    direction = "Down";
                } else if (dy < 0) {
                    direction = "Left";
                } else if (dy > 0) {
                    direction = "Right";
                }

                System.out.printf("%d. Move %s to (%d, %d)\n", i + 1, direction, currentCell[1] + 1, currentCell[0] + 1);
            }
            System.out.println((shortestPath.size() + 1) + ". Done!");
        } else {
            System.out.println("No valid path found.");
        }
    }


}
