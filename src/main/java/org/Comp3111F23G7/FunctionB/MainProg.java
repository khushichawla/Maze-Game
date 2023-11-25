package org.Comp3111F23G7.FunctionB;
import org.Comp3111F23G7.FunctionA.MazeGUI;
import org.Comp3111F23G7.FunctionA.MazeGenerator;
import org.Comp3111F23G7.Vertex;

import javax.swing.*;
import java.util.List;

public class MainProg {
    public static void main(String[] args) {
        int rows = 30;
        int cols = 30;

        MazeGenerator mazeGenerator = new MazeGenerator(rows, cols);
        mazeGenerator.generateMaze();
        mazeGenerator.saveMazeToFile("maze.txt");


        int[][] maze = MazeGUI.loadMazeFromFile("maze.txt");

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
//            m.printPaths(altpaths);
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
