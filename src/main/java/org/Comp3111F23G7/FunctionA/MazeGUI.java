package org.Comp3111F23G7.FunctionA;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeGUI {
    // private int[][] maze;

    public static void main(String[] args) {
        // Specify the path to the text file containing the maze
        String mazeFilePath = "maze.txt";

        // Load the maze from the text file
        int[][] maze = loadMazeFromFile(mazeFilePath);

        // Create and show the GUI maze
        SwingUtilities.invokeLater(() -> createAndShowMazeGUI(maze));
    }

    public static int[][] loadMazeFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            int[][] maze = null;

            while ((line = reader.readLine()) != null) {
                if (maze == null) {
                    int rows = line.length();
                    int cols = line.length();
                    maze = new int[rows][cols];
                }

                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    maze[row][col] = Character.getNumericValue(c);
                }

                row++;
            }

            return maze;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void createAndShowMazeGUI(int[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;

        JFrame frame = new JFrame("Maze GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(rows, cols));

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JPanel panel = new JPanel();
                if (maze[row][col] == 2) {
                    panel.setBackground(Color.GREEN);
                } else if (maze[row][col] == 3) {
                    panel.setBackground(Color.RED);
                } else if (maze[row][col] == 1) {
                    panel.setBackground(Color.WHITE);
                } else if (maze[row][col] == 4) {
                    panel.setBackground(Color.black);
                } else panel.setBackground(Color.GRAY);
                // panel.setBackground(maze[row][col] == 0 ? Color.GRAY : Color.WHITE);
                frame.add(panel);
            }
        }

        frame.pack();
        frame.setVisible(true);
    }
}