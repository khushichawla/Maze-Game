package org.Comp3111F23G7;

import org.Comp3111F23G7.FunctionA.MazeGUI;
import org.Comp3111F23G7.FunctionA.MazeGenerator;
import org.Comp3111F23G7.FunctionB.OutputMaze;
import org.Comp3111F23G7.FunctionB.Searcher;
import java.util.List;
import org.Comp3111F23G7.Vertex;

import javax.swing.*;


public class mainProject {
    public static void main(String[] args) {
        int rows = 30;
        int cols = 30;

        MazeGenerator mazeGenerator = new MazeGenerator(rows, cols);
        mazeGenerator.generateMaze();
        mazeGenerator.saveMazeToFile("maze.txt");

        // Load the maze from the text file
        int[][] maze = MazeGUI.loadMazeFromFile("maze.txt");

//         Create and show the GUI maze
        SwingUtilities.invokeLater(() -> MazeGUI.createAndShowMazeGUI(maze));
//        MazeGUI mazeGUI = new MazeGUI(maze);

        Searcher s = new Searcher(maze);
        Vertex[] path = s.bfs(mazeGenerator.getPointStart(),mazeGenerator.getPointEnd());
        int shortestpathlen = path.length;
        List<Vertex[]> altpaths = s.findDistinctPaths(mazeGenerator.getPointStart(),mazeGenerator.getPointEnd(), 5);
        OutputMaze m = null;
        try {
            m = new OutputMaze(maze, shortestpathlen, mazeGenerator.getPointStart(),mazeGenerator.getPointEnd());
            m.colorMazeWithPath(path);
            m.colorMazeWithMultiPath(altpaths, shortestpathlen);
            m.outputTextMaze();
            // m.printPaths(altpaths);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (m != null) {
                m.closeFileWriter();
            }
        }

//        int[][] mazeSP = MazeGUI.loadMazeFromFile("maze_output.txt");
//        SwingUtilities.invokeLater(() -> MazeGUI.createAndShowMazeGUI(mazeSP));
        int[][] mazeSP = MazeGUI.loadMazeFromFile("maze_output.txt");
        SwingUtilities.invokeLater(() -> MazeGUI.createAndShowMazeGUI(mazeSP));

    }
}
