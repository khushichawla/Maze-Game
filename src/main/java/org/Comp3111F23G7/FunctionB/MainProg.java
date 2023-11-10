package org.Comp3111F23G7.FunctionB;

import org.Comp3111F23G7.Vertex;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class MainProg {
    public static void main(String[] args){
        int[][] matrix =
                        {{1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
                        {0,1,1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,1,0,0,1,0,0,0,1,0,0,0,0,0},
                        {0,0,1,1,0,0,1,1,0,1,1,1,0,1,0,0,0,1,1,0,1,1,0,1,1,1,0,1,1,1},
                        {1,1,0,1,1,0,0,1,0,0,0,1,0,1,1,1,1,0,1,1,0,1,0,1,0,1,0,1,0,1},
                        {0,1,0,0,1,0,0,1,1,1,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
                        {0,1,0,0,1,1,1,0,0,1,0,1,0,0,1,0,1,0,0,1,1,0,1,1,0,0,0,1,0,1},
                        {1,1,1,0,0,0,1,1,0,1,0,1,1,1,1,0,1,1,1,0,1,0,0,0,0,1,1,1,0,1},
                        {1,0,1,1,0,0,0,1,1,1,0,0,1,0,0,0,0,0,1,0,1,1,1,0,0,1,0,0,1,1},
                        {1,0,0,1,1,1,0,0,0,0,0,1,1,0,1,0,1,0,1,1,0,0,0,0,1,1,0,1,1,0},
                        {1,0,0,0,0,1,1,1,0,1,1,1,0,0,0,0,1,0,0,1,1,1,0,1,1,0,1,1,0,1},
                        {1,0,0,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,0,0,1,0,0,0,0,0,1,0,0,1},
                        {1,1,1,1,0,1,0,1,1,1,0,1,0,0,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1},
                        {0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,0},
                        {1,1,0,0,0,1,1,1,0,1,1,1,0,1,0,0,0,0,1,0,0,0,1,1,1,1,0,1,1,0},
                        {0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,1,1,1,1,0,1,1,0,0,1,0,0,1,1},
                        {0,1,0,1,1,1,1,0,1,0,0,0,0,1,1,1,0,0,0,0,0,1,0,1,1,1,1,1,0,1},
                        {1,1,1,1,0,0,0,0,1,1,1,1,0,1,0,0,0,1,1,1,0,1,0,1,0,0,0,0,0,1},
                        {1,0,0,1,1,1,0,1,0,0,0,1,0,0,0,1,0,1,0,1,1,1,0,1,1,0,1,1,1,1},
                        {1,1,0,0,0,1,1,0,0,1,0,1,0,0,1,1,0,0,0,0,0,0,0,0,1,0,1,0,1,0},
                        {0,1,1,0,0,0,1,1,0,1,0,1,1,1,1,0,0,0,1,1,1,1,1,0,1,0,1,0,1,1},
                        {1,0,1,1,1,1,0,1,0,1,0,0,0,0,0,0,0,1,1,0,1,0,0,0,1,0,1,0,0,1},
                        {1,0,0,0,0,1,0,1,0,1,1,1,0,1,1,1,1,1,0,0,1,0,1,1,1,0,1,0,1,1},
                        {1,1,1,1,1,1,0,1,0,0,0,1,1,1,0,1,0,1,1,0,1,0,1,0,0,0,0,0,1,0},
                        {1,0,0,0,0,0,0,0,1,1,0,0,0,1,0,1,0,0,0,0,1,0,1,0,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,0,1,1,0,0,1,0,0,1,1,1,0,1,0,1,0,0,0,1,0,0,1},
                        {0,0,1,0,0,1,0,1,0,0,1,1,0,0,0,0,1,0,1,0,1,0,1,1,0,0,1,0,0,0},
                        {1,0,1,1,1,1,0,1,0,1,1,0,0,1,1,0,1,0,1,0,1,0,0,1,1,0,1,1,1,1},
                        {1,0,0,0,0,0,0,1,0,1,0,0,0,0,1,1,1,0,1,0,1,0,0,0,1,0,0,0,0,1},
                        {1,1,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,0,1,1,0,0,1,1,1,1,0,0,1,1},
                        {1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,0,1,1,1,1,0}};
        Searcher s =  new Searcher(matrix);
        Vertex[] path = s.dijkstra(new Vertex(0,12), new Vertex(29,1));
        int shortestpathlen = path.length;
        List<Vertex[]> altpaths = s.findDistinctPaths(new Vertex(0,12), new Vertex(29,1),5);
        s.colorMazeWithPath(path, 2);
        Iterator<Vertex[]> itr = altpaths.iterator();
        while (itr.hasNext()) {
            System.out.println("there is A path");
            Vertex[] p = itr.next();
            System.out.println(p.length);
            if (p.length == shortestpathlen) {
                s.colorMazeWithPath(p, 2);
                itr.remove();
            }
        }
        itr = altpaths.listIterator();
        while (itr.hasNext()) {
            System.out.println("there is B path");
            Vertex[] p = itr.next();
            System.out.println(p.length);
            s.colorMazeWithPath(p, 5);
        }
        System.out.println(shortestpathlen);

        try{
            s.outputMaze();
        }
        catch (Exception e){
            System.out.println(e);
        }

        for(Vertex v :path){
            System.out.print("[");
            System.out.print(v.getX());
            System.out.print(" ");
            System.out.print(v.getY());
            System.out.print("]");
        }
        System.out.println("\nfound shortest path");


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
