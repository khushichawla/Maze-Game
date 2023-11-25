package org.Comp3111F23G7;

import org.Comp3111F23G7.FunctionA.MazeGUI;
import org.Comp3111F23G7.FunctionA.MazeGenerator;
import org.Comp3111F23G7.FunctionC.Game;
import javax.swing.*;

public class mainProject {
    public static void main(String[] args) {

        MazeGenerator mazeGenerator = new MazeGenerator(30, 30);
        mazeGenerator.generateMaze();
        mazeGenerator.saveMazeToFile("maze.txt");
        int[][] maze = MazeGUI.loadMazeFromFile("maze.txt");

        Vertex start = mazeGenerator.getPointStart();
        Vertex end = mazeGenerator.getPointEnd();

        Game game = new Game();

        int[] jerry = {start.getY(), start.getX()+1};
        int[] oldJerry={-1,-1};
        int[] tom = {end.getY(), end.getX()-1};
        int[] oldTom={-1,-1};

        Game.updateMaze(maze,jerry,oldJerry,tom, oldTom, end);
        for(int i=0; i<30; i++){
            for(int j=0; j<30; j++){
                mazeGenerator.maze[i][j] = (char) (maze[i][j] + '0');
            }
        }
        mazeGenerator.saveMazeToFile("maze.txt");
        int[][] nmaze = MazeGUI.loadMazeFromFile("maze.txt");
        SwingUtilities.invokeLater(() -> MazeGUI.createAndShowMazeGUI(nmaze));

        game.playGame(maze,jerry, tom, mazeGenerator, end);
    }
}
