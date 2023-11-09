package org.Comp3111F23G7.FunctionB;

import org.Comp3111F23G7.Vertex;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.security.spec.ECField;
import java.util.LinkedList;
import java.util.Queue;

public class Searcher {

    private int[][] maze_matrix;

    public Searcher(int[][] maze_matrix) {
        this.maze_matrix = maze_matrix;
    }

    public Vertex[] Bfs(Vertex start, Vertex end) {
        if (maze_matrix[start.getY()][start.getX()] == 1 || maze_matrix[end.getY()][end.getX()] == 1) {
            return new Vertex[0]; // Start or end is a wall
        }

        boolean[][] visited = new boolean[maze_matrix.length][maze_matrix[0].length];
        Queue<NodeState> queue = new LinkedList<>();

        // Enqueue start vertex
        queue.add(new NodeState(start, null));
        visited[start.getY()][start.getX()] = true;

        NodeState endNode = null;

        while (!queue.isEmpty()) {
            NodeState current = queue.remove();

            // If we reached the end, break
            if (current.vertex.getX() == end.getX() && current.vertex.getY() == end.getY()) {
                endNode = current;
                break;
            }

            // Explore neighbors
            for (int[] direction : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) { // down, right, up, left
                int newX = current.vertex.getX() + direction[0];
                int newY = current.vertex.getY() + direction[1];

                if (newX >= 0 && newY >= 0 && newX < maze_matrix[0].length && newY < maze_matrix.length) {
                    if (!visited[newY][newX] && maze_matrix[newY][newX] == 0) {
                        visited[newY][newX] = true;
                        queue.add(new NodeState(new Vertex(newX, newY), current));
                    }
                }
            }
        }

        if (endNode == null) {
            return new Vertex[0]; // No path found
        }

        // Construct path
        LinkedList<Vertex> path = new LinkedList<>();
        NodeState current = endNode;
        while (current != null) {
            path.addFirst(current.vertex);
            current = current.parent;
        }

        return path.toArray(new Vertex[0]);
    }

    public Vertex[] dijkstra(Vertex start, Vertex end) {
        // Initialize distances array with infinity
        int[][] distances = new int[maze_matrix.length][maze_matrix[0].length];
        for (int[] row : distances) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distances[start.getY()][start.getX()] = 0;

        // Priority queue to keep track of vertices to be evaluated
        PriorityQueue<NodeState> queue = new PriorityQueue<>(Comparator.comparingInt(ns -> distances[ns.vertex.getY()][ns.vertex.getX()]));

        // Initialize prev array to reconstruct the path later
        Vertex[][] prev = new Vertex[maze_matrix.length][maze_matrix[0].length];

        // Add the start vertex to the queue
        queue.add(new NodeState(start, null));

        while (!queue.isEmpty()) {
            NodeState currentState = queue.poll();
            Vertex currentVertex = currentState.vertex;

            // If we reached the end vertex
            if (currentVertex.equals(end)) {
                break;
            }

            // Visit each neighbor of the current vertex
            for (int[] direction : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) { // down, right, up, left
                int neighborX = currentVertex.getX() + direction[0];
                int neighborY = currentVertex.getY() + direction[1];

                // Skip if out of bounds or it's a wall
                if (neighborX < 0 || neighborY < 0 || neighborX >= maze_matrix[0].length || neighborY >= maze_matrix.length || maze_matrix[neighborY][neighborX] == 1) {
                    continue;
                }

                // Calculate new distance
                int newDist = distances[currentVertex.getY()][currentVertex.getX()] + 1;
                if (newDist < distances[neighborY][neighborX]) {
                    distances[neighborY][neighborX] = newDist;
                    prev[neighborY][neighborX] = currentVertex;
                    queue.add(new NodeState(new Vertex(neighborX, neighborY), currentState));
                }
            }
        }

        // Reconstruct the path from end to start
        List<Vertex> path = new ArrayList<>();
        for (Vertex at = end; at != null && prev[at.getY()][at.getX()] != null; at = prev[at.getY()][at.getX()]) {
            path.add(at);
        }
        path.add(start); // Add the start vertex

        Collections.reverse(path);

        // Convert to Vertex array
        return path.toArray(new Vertex[0]);
    }

    public void outputMazeWithPath(Vertex[] path) throws IOException {
        // Copy maze matrix
        int[][] outputMatrix = new int[30][30];
        for (int i = 0; i< maze_matrix.length; ++i){
            for (int j = 0; j< maze_matrix.length; ++j){
                if (maze_matrix[j][i] == 0){
                    outputMatrix[j][i] = 1;
                }
                else
                    outputMatrix[j][i] = 4;
            }
        }
        for (Vertex v: path){
            int x_loc = v.getX();
            int y_loc = v.getY();
            outputMatrix[y_loc][x_loc] = 2;
        }

        // Write to file
        try (FileWriter writer = new FileWriter("maze_output.txt")) {
            for (int[] row : outputMatrix) {
                for (int cell : row) {
                    writer.write(Integer.toString(cell));
                }
                writer.write("\n");
            }
        }
        catch (Exception e){
            System.out.print(e);
        }
    }

    private class NodeState {
        Vertex vertex;
        NodeState parent;

        NodeState(Vertex vertex, NodeState parent) {
            this.vertex = vertex;
            this.parent = parent;
        }
    }
}




