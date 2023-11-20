package org.Comp3111F23G7.FunctionA;
import javax.swing.*;

/**
 * Main class to run the maze generator function
 */
public class MainProgram {
    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        int rows = 30;
        int cols = 30;

        MazeGenerator mazeGenerator = new MazeGenerator(rows, cols);
        mazeGenerator.generateMaze();
        mazeGenerator.saveMazeToFile("maze.txt");

        // Load the maze from the text file
        int[][] maze = MazeGUI.loadMazeFromFile("maze.txt");

        // Create and show the GUI maze
        SwingUtilities.invokeLater(() -> MazeGUI.createAndShowMazeGUI(maze));
    }
}
