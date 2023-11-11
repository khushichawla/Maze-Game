package org.Comp3111F23G7;

import org.Comp3111F23G7.FunctionB.Searcher;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        int[][] arrayMaze = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1},
                {1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1},
                {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
                {1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        int[] jerry = {12, 0};
        int[] oldJerry={-1,-1};
        int[] tom = {1,29};
        int[] oldTom={-1,-1};
        updateMaze(arrayMaze,jerry,oldJerry,tom, oldTom);
        printMaze(arrayMaze);
        playGame(arrayMaze,jerry, tom);
    }

    public static void printMaze(int[][] maze) {
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void updateMaze(int[][] maze, int[] jerry, int[] oldJerry, int[] tom, int[] oldTom) {
        int rowJ = jerry[0];
        int colJ = jerry[1];
        if(maze[rowJ][colJ] == 0){
            maze[rowJ][colJ]=2;
        }
        if(oldJerry[0]!=-1 && oldJerry[1]!=-1){
            int rowOJ = oldJerry[0];
            int colOJ = oldJerry[1];
            maze[rowOJ][colOJ]=0;
        }
        int rowT = tom[0];
        int colT = tom[1];
        if(maze[rowT][colT] == 0){
            maze[rowT][colT]=3;
        }
        if(oldTom[0]!=-1 && oldTom[1]!=-1){
            int rowOT = oldTom[0];
            int colOT = oldTom[1];
            maze[rowOT][colOT]=0;
        }
    }

    private static void playGame(int[][] maze, int[] jerry, int[] tom) {
        Scanner scanner = new Scanner(System.in);
        Searcher s =  new Searcher(maze);
        int countV=0;
        Vertex[] path = s.dijkstra(new Vertex(28,1), new Vertex(0,12));

        int[] oldJerry = new int[2];
        int[] oldTom = new int[2];
        // Get the player's input for Jerry's move
        while(true){
            System.out.print("Enter Jerry's next move (WASD), Press Q to exit the game: ");
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
                updateMaze(maze, jerry, oldJerry, tom, oldTom);
                for(int i=0; i<2; i++){
                    int y = path[countV].getX();
                    int x = path[countV].getY();
                    countV++;
                    oldTom[0] = tom[0];
                    oldTom[1] = tom[1];
                    tom[0] = x;
                    tom[1] = y;
                    updateMaze(maze, jerry, oldJerry, tom, oldTom);
                }
                updateMaze(maze, jerry, oldJerry, tom, oldTom);
            }

            else if (isValidMove(jerryMove, maze)==false){
                System.out.println("Invalid move! Try again.");
            }

            printMaze(maze);
            if(jerry[0]==1 && jerry[1]==29){
                System.out.println("You won! :)");
                break;
            }
            if(jerry[0]==tom[0] && jerry[1]==tom[1]){
                System.out.println("You lost :(");
                break;
            }
        }
    }

    private static int[] calculateNextMove(int[] currentPos, String input) {
        int[] nextMove = new int[2];
        // Determine the next move based on the player's input
        switch (input.toLowerCase()) {
            case "q" -> {
                nextMove[0] = -1; // Special value to indicate an exit move
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

    private static boolean isValidMove(int[] move, int[][] maze) {
        if(move[0]==30 || move[1]==30){
            return false;
        }
        int row = move[0];
        int col = move[1];
        return maze[row][col] == 0;
    }
}