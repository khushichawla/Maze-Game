package org.Comp3111F23G7.FunctionB;

import org.Comp3111F23G7.Vertex;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class OutputMaze {
    private int[][] output_matrix;

    public OutputMaze (int[][] maze_matrix){
        this.output_matrix = new int[30][30];
        for (int i = 0; i< maze_matrix.length; ++i){
            for (int j = 0; j< maze_matrix.length; ++j) {
                if (maze_matrix[j][i] == 0) {
                    output_matrix[j][i] = 1;
                } else
                    output_matrix[j][i] = 4;
            }
        }
    }

    public void colorMazeWithPath(Vertex[] path, int color){
        for (Vertex v: path){
            int x_loc = v.getX();
            int y_loc = v.getY();
            output_matrix[y_loc][x_loc] = color;
        }
    }

    public void colorMazeWithMultiPath(List<Vertex[]> altpaths, int shortestpathlen){
        Iterator<Vertex[]> itr = altpaths.iterator();
        while (itr.hasNext()) {
            Vertex[] p = itr.next();
            if (p.length == shortestpathlen) {
                this.colorMazeWithPath(p, 2);
                itr.remove();
            }
        }
        itr = altpaths.listIterator();
        while (itr.hasNext()) {
            Vertex[] p = itr.next();
            this.colorMazeWithPath(p, 5);
        }
    }

    public void outputMaze() throws IOException {
        try (FileWriter writer = new FileWriter("maze_output.txt")) {
            for (int[] row : output_matrix) {
                for (int cell : row) {
                    writer.write(Integer.toString(cell));
                }
                writer.write("\n");
            }
        }
    }

    public void printPath(Vertex[] path){
        for(Vertex v :path){
            System.out.print("[");
            System.out.print(v.getX());
            System.out.print(" ");
            System.out.print(v.getY());
            System.out.print("]");
        }
        System.out.println("\nfound shortest path");
    }
    public void printPaths(List<Vertex[]> altpaths){
        for (Vertex[] i : altpaths){
            System.out.println("\nThe path: ");
            for(Vertex v :i){
                System.out.print("[");
                System.out.print(v.getX());
                System.out.print(" ");
                System.out.print(v.getY());
                System.out.print("]");
            }
        }
        System.out.println("\nfound paths");
    }

}

