package org.Comp3111F23G7.FunctionA;

import org.Comp3111F23G7.KeyboardListener;
import org.Comp3111F23G7.ThreadsController;
import org.Comp3111F23G7.Vertex;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * MazeGUI class
 */
public class MazeGUI {
    public static boolean isVisible = false;
    public static JFrame frame = new JFrame("Maze Game");
    private static JPanel[][] mazePanels;
    private static int[][] playerLocations;
    private static int[][] maze;

    public MazeGUI(int[][] maze) {
        this.maze = maze;
    }

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
        int rows = maze.length;
        int cols = maze[0].length;

//        JFrame frame = new JFrame("Maze Game");
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(rows, cols));
        mazePanels = new JPanel[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JPanel panel = new JPanel();
                panel.setBackground(getCellColor(maze[row][col]));
                mazePanels[row][col] = panel;
                frame.add(panel);
            }
        }

        isVisible = true;
        frame.pack();
        frame.setVisible(true);
        frame.revalidate(); // Refresh the frame
        frame.repaint();
    }

    private static Color getCellColor(int cellValue) {
        switch (cellValue) {
            case 0:
                return Color.WHITE; // Clear path
            case 2:
                return Color.BLUE; // Shortest Path
            case 3:
                return Color.RED; // Exit Point
            case 4:
                return Color.BLACK; // Maze Border
            case 5:
                return Color.YELLOW; // All Possible Paths
            case 6:
                return Color.ORANGE; // Jerry
            case 7:
                return Color.GREEN; // Entry Point
            case 8:
                return Color.PINK; // Tom
            default:
                return Color.GRAY; // Wall
        }
    }

    public boolean isGUIVisible() {
        return isVisible;
    }
}