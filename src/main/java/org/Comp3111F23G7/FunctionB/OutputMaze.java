/**
 * The {@code OutputMaze} class is responsible for visualizing and outputting maze data.
 * It creates a visual representation of paths within a maze and writes it to a file.
 * The class also supports coloring paths differently based on whether they are the shortest path or not.
 * It handles file operations for both text and CSV format outputs.
 *
 * @author Virginia Tsang
 * @version 1.0
 */
package org.Comp3111F23G7.FunctionB;

import org.Comp3111F23G7.Vertex;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class OutputMaze {
    private int[][] output_matrix;
    private FileWriter fileWriter;
    private int sp_cnt;
    private int op_cnt;
    private int shortestpathlen;
    private Vertex start;
    private Vertex end;

    /**
     * Constructs a new {@code OutputMaze} with the specified parameters.
     * Initializes the output matrix and sets up the file writer.
     *
     * @param maze_matrix The 2D integer array representing the maze.
     * @param shortestpathlen The length of the shortest path.
     * @param start The starting vertex of the maze.
     * @param end The ending vertex of the maze.
     * @throws IOException If an I/O error occurs.
     */
    public OutputMaze (int[][] maze_matrix, int shortestpathlen, Vertex start, Vertex end) throws IOException {
        this.output_matrix = new int[30][30];
        for (int i = 0; i< maze_matrix.length; ++i) {
            for (int j = 0; j < maze_matrix.length; ++j) {
                if (maze_matrix[j][i] == 0) {
                    output_matrix[j][i] = 0;
                } else
                    output_matrix[j][i] = 9;
            }
        }
        this.start = start;
        this.end = end;
        output_matrix[start.getY()][start.getX()] = 7; //xxx
        output_matrix[end.getY()][end.getX()] = 3;
        try {
            this.fileWriter = new FileWriter("src/main/java/org/Comp3111F23G7/FunctionB/paths.csv", true); // true for append mode
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sp_cnt=0;
        this.op_cnt=0;
        this.shortestpathlen = shortestpathlen;
        }
    /**
     * Colors the maze with the provided path.
     * Paths are colored differently based on whether they are the shortest path or not.
     * Also writes the path information to a CSV file.
     *
     * @param path An array of {@code Vertex} objects representing a path in the maze.
     */
    public void colorMazeWithPath(Vertex[] path) {
        boolean is_sp = path.length == shortestpathlen;
        int color = is_sp ? 2 : 5;
        StringBuilder csvLine = new StringBuilder();

        if (is_sp) {
            csvLine.append("SP, ").append(++sp_cnt);
        } else {
            csvLine.append("OP, ").append(++op_cnt);
        }

        int index = 1;
        for (Vertex v : path) {
            int x_loc = v.getX();
            int y_loc = v.getY();
            output_matrix[y_loc][x_loc] = color;
            csvLine.append(", (").append(index++).append(",").append(x_loc).append(",").append(y_loc).append(")");
        }
        csvLine.append(";");

        output_matrix[start.getY()][start.getX()] = 7; //xxx
        output_matrix[end.getY()][end.getX()] = 3;

        try {
            fileWriter.write(csvLine.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Closes the file writer.
     * Should be called after all write operations are completed.
     */
    public void closeFileWriter() {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Colors the maze with multiple paths and distinguishes between shortest and other paths.
     *
     * @param altpaths A list of vertex arrays, each representing a path in the maze.
     * @param shortestpathlen The length of the shortest path for comparison.
     */
    public void colorMazeWithMultiPath(List<Vertex[]> altpaths, int shortestpathlen){
        Iterator<Vertex[]> itr = altpaths.iterator();
        while (itr.hasNext()) {
            Vertex[] p = itr.next();
            if (p.length == shortestpathlen) {
                this.colorMazeWithPath(p);
                itr.remove();
            }
        }
        itr = altpaths.listIterator();
        while (itr.hasNext()) {
            Vertex[] p = itr.next();
            this.colorMazeWithPath(p);
        }
    }
    /**
     * Outputs the visual representation of the maze to a text file.
     * The method writes a bordered maze with designated symbols for paths, walls, start, and end points.
     *
     * @throws IOException If an I/O error occurs during writing.
     */
    public void outputTextMaze() throws IOException {
        try (FileWriter writer = new FileWriter("maze_output.txt")) {
            writer.write("44444444444444444444444444444444\n");
            for (int[] row : output_matrix) {
                    writer.write(Integer.toString(4));
                for (int cell : row) {
                    writer.write(Integer.toString(cell));
                }
                writer.write(Integer.toString(4));
                writer.write("\n");
            }
            writer.write("44444444444444444444444444444444");
        }
    }

//    public void printPath(Vertex[] path){
//        System.out.println("\nfound shortest path, its length: "+ path.length);
//        for(Vertex v :path){
//            System.out.print("[");
//            System.out.print(v.getX());
//            System.out.print(" ");
//            System.out.print(v.getY());
//            System.out.print("]");
//        }
//
//    }
//    public void printPaths(List<Vertex[]> altpaths){
//        System.out.println("\nfound other paths");
//        for (Vertex[] i : altpaths) {
//            System.out.println("\nThe path, its length: " + i.length);
//            for (Vertex v : i) {
//                System.out.print("[");
//                System.out.print(v.getX());
//                System.out.print(" ");
//                System.out.print(v.getY());
//                System.out.print("]");
//            }
//        }
//    }
}

