package org.Comp3111F23G7.FunctionA;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MazeGenerator {
    private char[][] maze;
    private int rows;
    private int cols;
    private Point start;
    private Point end;

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

        // Iterate through direct neighbors of the start node
        ArrayList<Point> frontier = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x == 0 && y == 0) || (x != 0 && y != 0)) {
                    continue;
                }
                try {
                    if (maze[start.r + x][start.c + y + 1] == '1') {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
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

        // Perform pathfinding algorithm to find a path from start to end
        boolean[][] visited = new boolean[rows][cols];
        boolean pathFound = findPath(maze, start.r, start.c, end.r, end.c, visited);

        // If there is only one path, create a branching path
        if (!pathFound) {
            // Randomly select a point on the existing path
            int randomRow = start.r;
            int randomCol = start.c;
            while (randomRow == start.r && randomCol == start.c) {
                randomRow = (int) (Math.random() * rows);
                randomCol = (int) (Math.random() * cols);
            }

            // Remove the wall adjacent to the selected point
            maze[randomRow][randomCol] = '1';
            maze[randomRow][randomCol + 1] = '1';
        }
    }

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

    static class Point {
        Integer r;
        Integer c;
        Point parent;
        boolean isExit;

        public Point(int x, int y, Point p) {
            r = x;
            c = y;
            parent = p;
            isExit = false;
        }

        // Compute opposite node given that it is in the other direction from the parent
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

    private static boolean findPath(char[][] maze, int startRow, int startCol, int endRow, int endCol, boolean[][] visited) {
        if (startRow == endRow && startCol == endCol) {
            return true; // Path found
        }

        visited[startRow][startCol] = true;

        // Explore neighbors in the four cardinal directions
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int newRow = startRow + dx[i];
            int newCol = startCol + dy[i];

            if (isValidMove(maze, newRow, newCol, visited)) {
                if (findPath(maze, newRow, newCol, endRow, endCol, visited)) {
                    return true;
                }
            }
        }

        return false; // Path not found
    }

    private static boolean isValidMove(char[][] maze, int row, int col, boolean[][] visited) {
        int numRows = maze.length;
        int numCols = maze[0].length;

        // Check if the move is within the boundaries of the maze
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            return false;
        }

        // Check if the move leads to a valid path (not a wall) and has not been visited before
        if (maze[row][col] == '0' || visited[row][col]) {
            return false;
        }

        return true; // Move is valid
    }
}