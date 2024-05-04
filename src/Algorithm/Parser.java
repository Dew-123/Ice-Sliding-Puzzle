/**
 * @author Risandu Disanayake
 * @author 20220177 w1953141
 */

package Algorithm;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class Parser {

    private int[] beginPoint;
    private int[] endPoint;
    private int[][] maze;

    private final ArrayList<String> lines = new ArrayList<>();
    private boolean hasRead;
    private boolean hasLoaded;
    private File inputFile;

    // Getter for the starting point of the maze
    public int[] getBeginPoint() {
        return hasLoaded ? beginPoint : null;
    }

    // Getter for the ending point of the maze
    public int[] getEndPoint() {
        return hasLoaded ? endPoint : null;
    }

    // Getter for the maze structure
    public int[][] getMaze() {
        return hasLoaded ? maze : null;
    }

    // Method to load lines from a file
    public void loadLines() throws IOException {
        if (this.hasRead) {
            lines.addAll(Files.readAllLines(inputFile.toPath(), Charset.defaultCharset())); //https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
            this.hasLoaded = true;
        }
    }

    // Getter for the list of lines read from the file
    public ArrayList<String> getLines() {
        return hasRead ? lines : null;
    }

    // Method to read a file using a file dialog
    public void readFile() throws Exception {
        FileDialog fileDialog = new FileDialog((Frame) null, "Select input file"); //https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/awt/FileDialog.html
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setDirectory(System.getProperty("user.dir"));
        fileDialog.setFile("*.txt");
        fileDialog.setVisible(true);
        String file = fileDialog.getFile();
        String fileType = file.substring(Math.max(0, file.length() - 4));

        if (!fileType.equals(".txt")) {
            throw new Exception("File extension of the input file should be .txt");
        }

        File inputFile = fileDialog.getFiles()[0];
        if (inputFile.length() == 0) {
            throw new Exception("No file selected");
        }

        this.inputFile = inputFile;
        this.hasRead = true;
    }

    // Method to load values from the lines read from the file and populate the maze
    public boolean loadValues() {
        ArrayList<String> lines = this.getLines();
        if (lines.isEmpty()) return false;

        int floorSize = lines.get(0).trim().length();
        this.maze = new int[lines.size()][floorSize];

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            // Check if the line is empty
            if (line.isEmpty()) {
                continue;
            }

            int[] floor = new int[floorSize];

            for (int j = 0; j < floorSize; j++) {
                // Check if the index is within bounds
                if (j >= line.length()) {
                    break;
                }

                char ch = line.charAt(j);
                if (ch == '0') {
                    floor[j] = 1;
                } else if (ch == '.') {
                    floor[j] = 0;
                } else if (ch == 'S') {
                    this.beginPoint = new int[]{i, j};
                    floor[j] = 0;
                } else if (ch == 'F') {
                    this.endPoint = new int[]{i, j};
                    floor[j] = 0;
                }
            }
            maze[i] = floor;
        }
        return true;
    }
}
