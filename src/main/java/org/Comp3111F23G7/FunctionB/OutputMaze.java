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

    public OutputMaze (int[][] maze_matrix, int shortestpathlen) throws IOException {
        this.output_matrix = new int[30][30];
        for (int i = 0; i< maze_matrix.length; ++i) {
            for (int j = 0; j < maze_matrix.length; ++j) {
                if (maze_matrix[j][i] == 0) {
                    output_matrix[j][i] = 1;
                } else
                    output_matrix[j][i] = 4;
            }
        }
        try {
            this.fileWriter = new FileWriter("src/main/java/org/Comp3111F23G7/FunctionB/paths.csv", true); // true for append mode
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sp_cnt=0;
        this.op_cnt=0;
        this.shortestpathlen = shortestpathlen;
        }

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

        try {
            fileWriter.write(csvLine.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileWriter() {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void outputMaze() throws IOException {
        try (FileWriter writer = new FileWriter("src/main/java/org/Comp3111F23G7/maze_output.txt")) {
            for (int[] row : output_matrix) {
                for (int cell : row) {
                    writer.write(Integer.toString(cell));
                }
                writer.write("\n");
            }
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

