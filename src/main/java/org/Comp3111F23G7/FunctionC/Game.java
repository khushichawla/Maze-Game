package org.Comp3111F23G7.FunctionC;

import org.Comp3111F23G7.FunctionA.MazeGUI;
import org.Comp3111F23G7.FunctionA.MazeGenerator;
import org.Comp3111F23G7.FunctionB.Searcher;
import org.Comp3111F23G7.Vertex;
import javax.swing.*;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(30, 30);
        mazeGenerator.generateMaze();
        mazeGenerator.saveMazeToFile("maze.txt");
        int[][] maze = MazeGUI.loadMazeFromFile("maze.txt");
//        System.out.println(maze[0].length);
        Vertex start = mazeGenerator.getPointStart();
        Vertex end = mazeGenerator.getPointEnd();

        int[] jerry = {start.getY(), start.getX()+1};
        int[] oldJerry={-1,-1};
        int[] tom = {end.getY(), end.getX()-1};
        int[] oldTom={-1,-1};
//        System.out.println(start.getY());
//        System.out.println(start.getX());
        updateMaze(maze,jerry,oldJerry,tom, oldTom, end);
        for(int i=0; i<30; i++){
            for(int j=0; j<30; j++){
                mazeGenerator.maze[i][j] = (char) (maze[i][j] + '0');
            }
        }
        mazeGenerator.saveMazeToFile("maze.txt");
        int[][] nmaze = MazeGUI.loadMazeFromFile("maze.txt");
        SwingUtilities.invokeLater(() -> MazeGUI.createAndShowMazeGUI(nmaze));

        playGame(maze,jerry, tom, mazeGenerator, end);
    }

    /**
     * printMaze function to check if the given indices are valid
     * @param maze - 2d array of maze
     */
    public static void printMaze(int[][] maze) {
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * updateMaze function to check if the given indices are valid
     * @param maze - row index
     * @param jerry - column index
     * @param oldJerry - column index
     * @param tom - column index
     * @param oldTom - column index
//     * @return true if the indices are valid, false otherwise
     */
    public static void updateMaze(int[][] maze, int[] jerry, int[] oldJerry, int[] tom, int[] oldTom, Vertex end) {
        int rowJ = jerry[0];
        int colJ = jerry[1];
        if(maze[rowJ][colJ] == 0 ){
            maze[rowJ][colJ]=6;
        }
        if(colJ == 1){
            maze[rowJ][colJ]=6;
        }
        if(oldJerry[0]!=-1 && oldJerry[1]!=-1){
            int rowOJ = oldJerry[0];
            int colOJ = oldJerry[1];
            maze[rowOJ][colOJ]=0;
        }
        int rowT = tom[0];
        int colT = tom[1];
        if(maze[rowT][colT] == 0 || maze[rowT][colT]==3){
            maze[rowT][colT]=8;
        }

        if(oldTom[0]!=-1 && oldTom[1]!=-1){
            int rowOT = oldTom[0];
            int colOT = oldTom[1];
//            if(end.getY()==rowOT && end.getX()==colOT){
//                maze[rowT][colT]=3;
//            }
//            else{
                maze[rowOT][colOT]=0;
//            }
        }
    }

    public static void playGame(int[][] maze, int[] jerry, int[] tom, MazeGenerator mazeGenerator, Vertex end) {
        int[] exit= {tom[0], tom[1]};
        Scanner scanner = new Scanner(System.in);
        Searcher s =  new Searcher(maze);
        int countV=0;
        int rev=0;
        Vertex[] path = s.dijkstra(new Vertex(tom[1],tom[0]), new Vertex(jerry[1],jerry[0]));
        int size = path.length-1;

        int[] oldJerry = new int[2];
        int[] oldTom = new int[2];
        // Get the player's input for Jerry's move
        while(true){
            System.out.println("Jerry's position is denoted by orange square and Tom's position is denoted by pink square ");
            System.out.print("Enter Jerry's next move (WASD), Press Q to exit the game: ");
//            System.out.println("\n");
            String input = scanner.nextLine();
            int[] jerryMove = calculateNextMove(jerry, input);
            if (jerryMove[0] == -1 && jerryMove[1] == -1) {
                System.out.println("Exiting the game...");
                break;
            } else if (isValidMove(jerryMove, maze)) {
                oldJerry[0]=jerry[0];
                oldJerry[1]=jerry[1];
                jerry[0] = jerryMove[0];
                jerry[1] = jerryMove[1];
                updateMaze(maze, jerry, oldJerry, tom, oldTom, end);

                for(int i=0; i<2; i++){
                    int y = path[countV].getX();
                    int x = path[countV].getY();
                    if(countV<size && rev==0){
                        countV++;
                    }
                    if(countV == size){
                        rev=1;
                    }
                    if((countV<size && rev==1) || y==0) {
                        if (countV - 1 > 0) {
                            countV--;
                        } else {
                            countV++;
                        }
                    }
                    oldTom[0] = tom[0];
                    oldTom[1] = tom[1];
                    tom[0] = x;
                    tom[1] = y;
                    updateMaze(maze, jerry, oldJerry, tom, oldTom, end);
                }
                updateMaze(maze, jerry, oldJerry, tom, oldTom, end);
            }

            else if (isValidMove(jerryMove, maze)==false){
                System.out.println("Invalid move! Try again.");
            }
            for(int i=0; i<30; i++){
                for(int j=0; j<30; j++){
                    mazeGenerator.maze[i][j] = (char) (maze[i][j] + '0');
                }
            }
            mazeGenerator.saveMazeToFile("maze.txt");
            int[][] nmaze = MazeGUI.loadMazeFromFile("maze.txt");

            SwingUtilities.invokeLater(() -> MazeGUI.createAndShowMazeGUI(nmaze));
            if(jerry[0]==exit[0] && jerry[1]==exit[1]){
                System.out.println("You won! :)");
                break;
            }
            if(jerry[0]==tom[0] && jerry[1]==tom[1]){
                System.out.println("You lost :(");
                break;
            }
        }
    }

    public static int[] calculateNextMove(int[] currentPos, String input) {
        int[] nextMove = new int[2];
        switch (input.toLowerCase()) {
            case "q" -> {
                nextMove[0] = -1;
                nextMove[1] = -1;
            }
            case "w" -> {
                nextMove[0] = currentPos[0] - 1; // Move up
                nextMove[1] = currentPos[1];
            }
            case "a" -> {
                nextMove[0] = currentPos[0];
                nextMove[1] = currentPos[1] - 1; // Move left
            }
            case "s" -> {
                nextMove[0] = currentPos[0] + 1; // Move down
                nextMove[1] = currentPos[1];
            }
            case "d" -> {
                nextMove[0] = currentPos[0];
                nextMove[1] = currentPos[1] + 1; // Move right
            }
            default -> {
                nextMove[0] = currentPos[0]; // Stay in the same position
                nextMove[1] = currentPos[1];
            }
        }
        return nextMove;
    }

    public static boolean isValidMove(int[] move, int[][] maze) {
        if(move[0]==31 || move[1]==31 || move[0]==30 || move[1]==30){
            return false;
        }
        int row = move[0];
        int col = move[1];
        return maze[row][col] == 0;
    }


}
