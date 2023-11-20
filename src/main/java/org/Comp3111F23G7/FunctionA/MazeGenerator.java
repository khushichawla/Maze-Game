package org.Comp3111F23G7.FunctionA;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * MazeGenerator Class to generate the maze in a text file
 */
public class MazeGenerator {
    private char[][] maze;
    private int rows;
    private int cols;
    private Point start;
    private Point end;

    /**
     * Constructor 1
     * @param rows - int number of rows
     * @param cols - int number of columns
     */
    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        maze = new char[rows][cols];
    }

    public void generateMaze() {
        // Build maze and initialize with only walls
        maze = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = '0';
            }
        }

        // Select random point on the left border and open as the start node
        start = new Point((int) (Math.random() * rows), 0, null);
        maze[start.r][start.c] = '2';

        // Select random point on the right border and open as the end node
        end = new Point((int) (Math.random() * rows), cols - 1, null);
        maze[end.r][end.c] = '3';
        end.isExit = true;

        // Generate the maze using Prim's algorithm
        generateMazeUsingPrimsAlgorithm();

        // Add additional paths using DFS
        addAdditionalPathsUsingDFS();
    }

    /**
     * generateMazeUsingPrimsAlgorithm function to generate maze using Prim's algorithm
     */
    private void generateMazeUsingPrimsAlgorithm() {
        // Iterate through direct neighbors of the start node
        ArrayList<Point> frontier = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x == 0 && y == 0) || (x != 0 && y != 0)) {
                    continue;
                }
                if (maze[start.r + x][start.c + y + 1] == '1') {
                    continue;
                }
//                try {
//                    if (maze[start.r + x][start.c + y + 1] == '1') {
//                        continue;
//                    }
//                } catch (Exception e) {
//                    continue;
//                }
                // Add eligible points to the frontier
                frontier.add(new Point(start.r + x, start.c + y + 1, start));
            }
        }

        Point last = null;
        while (!frontier.isEmpty()) {
            // Pick the current node at random
            Point current = frontier.remove((int) (Math.random() * frontier.size()));
            Point opposite = current.opposite();
            try {
                // If both node and its opposite are walls
                if (maze[current.r][current.c] == '0') {
                    if (maze[opposite.r][opposite.c] == '0') {
                        // Open path between the nodes
                        maze[current.r][current.c] = '1';
                        maze[opposite.r][opposite.c] = '1';
                        // Store the last node in order to mark it later
                        last = opposite;
                        // Iterate through direct neighbors of the node
                        for (int x = -1; x <= 1; x++) {
                            for (int y = -1; y <= 1; y++) {
                                if ((x == 0 && y == 0) || (x != 0 && y != 0)) {
                                    continue;
                                }
                                try {
                                    if (maze[opposite.r + x][opposite.c + y] == '1') {
                                        continue;
                                    }
                                } catch (Exception e) {
                                    continue;
                                }
                                frontier.add(new Point(opposite.r + x, opposite.c + y, opposite));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                continue;
            }

            // If the algorithm has resolved, mark the end node
            if (frontier.isEmpty()) {
                maze[last.r][last.c] = '1';
                last.isExit = true;
            }
        }
    }

    /**
     * addAdditionalPathsUsingDFS function to add additional paths using DFS
     */
    private void addAdditionalPathsUsingDFS() {
        // Perform DFS to add additional paths
        boolean[][] visited = new boolean[rows][cols];
        int pathsAdded = 0; // Counter for paths added
        dfs(maze, start.r, start.c, visited, pathsAdded);
    }

    /**
     * dfs function to perform depth-first search
     * @param maze - generated maze
     * @param row - current row
     * @param col - current column
     * @param visited - boolean 2D array of visited indices
     * @param pathsAdded - number of paths added so far
     */
    private void dfs(char[][] maze, int row, int col, boolean[][] visited, int pathsAdded) {
        visited[row][col] = true;
        maze[row][col] = '1';

        // Perform DFS on the unvisited neighbors
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if (isValid(newRow, newCol) && !visited[newRow][newCol] && maze[newRow][newCol] != '1') {
                pathsAdded++;
                if (pathsAdded <= 2) { // Limit the number of additional paths to 2
                    int wallRow = row + dx[i] / 2; // Calculate the row of the wall to open
                    int wallCol = col + dy[i] / 2; // Calculate the column of the wall to open
                    maze[wallRow][wallCol] = '1'; // Open the wall
                    dfs(maze, newRow, newCol, visited, pathsAdded);
                }
            }
        }
    }

    /**
     * isValid function to check if the given indices are valid
     * @param row - row index
     * @param col - column index
     * @return true if the indices are valid, false otherwise
     */
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }



    /**
     * saveMazeToFile function to save generated maze in text file
     * @param filename - string name of file to save maze in
     */
    public void saveMazeToFile(String filename) {
        // Print final maze
        try (FileWriter writer = new FileWriter(filename)) {
            int ind1 = -1, ind2 = -1;
            boolean started = false;

            // adding top border to the maze
            for (int i = 0; i < 32; i++) {
                writer.write('4');
            }

            writer.write("\n");
            for (int i = 0; i < rows; i++) {
                if (i == start.r) {
                    writer.write('2');
                } else writer.write('4');
                for (int j = 0; j < cols; j++) {
                    if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                        if (i == start.r && j == start.c) {
                            writer.write('2'); // Entry point
                            started = true;
                            ind1 = i;
                            ind2 = j+1;
                        } else writer.write(maze[i][j]); // Walls for borders
                    } else {
                        if (i == end.r && j+1 == end.c) writer.write('1');
                        else if (i+1 == end.r && j == end.c) writer.write('1');
                        else if (started && ind1 == i && ind2 == j) {
                            writer.write('1');
                            started = false;
                        } else writer.write(maze[i][j]);
                    }
                }
                if (i == end.r) writer.write('3');
                else writer.write('4');
                writer.write("\n");
            }

            // adding bottom border to the maze
            for (int i = 0; i < 32; i++) {
                writer.write('4');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
    /**
     * Point class
     */
    static class Point {
        Integer r;
        Integer c;
        Point parent;
        boolean isExit;

        /**
         * Constructor
         * @param x - int x point
         * @param y - int y point
         * @param p - int p location
         */
        public Point(int x, int y, Point p) {
            r = x;
            c = y;
            parent = p;
            isExit = false;
        }

        /**
         * opposite function
         * @return - returns the opposite point node given that it is in the other direction from parent
         */
        public Point opposite() {
            if (this.r.compareTo(parent.r) != 0) {
                return new Point(this.r + this.r.compareTo(parent.r), this.c, this);
            }
            if (this.c.compareTo(parent.c) != 0) {
                return new Point(this.r, this.c + this.c.compareTo(parent.c), this);
            }
            return null;
        }
    }
}