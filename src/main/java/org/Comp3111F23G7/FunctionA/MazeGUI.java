package org.Comp3111F23G7.FunctionA;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * MazeGUI class
 */
public class MazeGUI {
    public static boolean isVisible = false;

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        // Specify the path to the text file containing the maze
        String mazeFilePath = "maze.txt";

        // Load the maze from the text file
        int[][] maze = loadMazeFromFile(mazeFilePath);

        // Create and show the GUI maze
        SwingUtilities.invokeLater(() -> createAndShowMazeGUI(maze));
    }

    /**
     * loadMazeFromFile
     * @param filePath - String name of file to load
     * @return - 2D int array of maze
     */
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

    /**
     * createAndShowMazeGUI
     * @param maze - int 2D array of maze
     */
    public static void createAndShowMazeGUI(int[][] maze) {
//        if (maze.length != 30) {
//            isVisible = false;
//            System.out.println("Invalid maze: Maze is null");
//            return;
//        }
        int rows = maze.length;
        int cols = maze[0].length;

        JFrame frame = new JFrame("Maze Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(rows, cols));

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JPanel panel = new JPanel();
                if (maze[row][col] == 2) {
                    panel.setBackground(Color.BLUE);
                } else if (maze[row][col] == 7) {
                    panel.setBackground(Color.GREEN);
                } else if (maze[row][col] == 3) {
                    panel.setBackground(Color.RED);
                } else if (maze[row][col] == 0) {
                    panel.setBackground(Color.WHITE);
                } else if (maze[row][col] == 4) {
                    panel.setBackground(Color.BLACK);
                } else if (maze[row][col] == 5) {
                    panel.setBackground(Color.YELLOW);
                } else panel.setBackground(Color.GRAY);
                // panel.setBackground(maze[row][col] == 0 ? Color.GRAY : Color.WHITE);
                frame.add(panel);
            }
        }

        isVisible = true;
        frame.pack();
        frame.setVisible(true);
    }

    public boolean isGUIVisible() {
        return isVisible;
    }
}