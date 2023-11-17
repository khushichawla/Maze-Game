package org.Comp3111F23G7.FunctionB;
import org.Comp3111F23G7.Vertex;

import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Searcher {
    private int[][] maze_matrix;
    private int[][] output_matrix;

    public Searcher(int[][] maze_matrix) {
        this.maze_matrix = maze_matrix;
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

    public Vertex[] bfs(Vertex start, Vertex end) {
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

        // Initialize the prev array to store the previous vertex on the shortest path
        Vertex[][] prev = new Vertex[maze_matrix.length][maze_matrix[0].length];

        // Priority queue to keep track of vertices to be evaluated
        PriorityQueue<NodeState> queue = new PriorityQueue<>(Comparator.comparingInt(ns -> distances[ns.vertex.getY()][ns.vertex.getX()]));

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
                int edgeWeight = maze_matrix[neighborY][neighborX] == 0 ? 1 : maze_matrix[neighborY][neighborX]; // Consider non-wall cells as having a weight of 1
                int newDist = distances[currentVertex.getY()][currentVertex.getX()] + edgeWeight;
                if (newDist < distances[neighborY][neighborX]) {
                    distances[neighborY][neighborX] = newDist;
                    prev[neighborY][neighborX] = currentVertex;
                    queue.add(new NodeState(new Vertex(neighborX, neighborY), currentState));
                }
            }
        }

        // Reconstruct the path from end to start
        List<Vertex> path = new ArrayList<>();
        Vertex at = end;
        if (prev[at.getY()][at.getX()] != null || at.equals(start)) {
            while (at != null) {
                path.add(at);
                at = prev[at.getY()][at.getX()];
            }
        }
        Collections.reverse(path);

        // Convert to Vertex array
        return path.toArray(new Vertex[0]);
    }

    private class Path {
        List<Vertex> vertices = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();

        Path() {
        }

        Path(Path other) {
            this.vertices = new ArrayList<>(other.vertices);
            this.visited = new HashSet<>(other.visited);
        }

        void add(Vertex v) {
            vertices.add(v);
            visited.add(v);
        }

        boolean contains(Vertex v) {
            return visited.contains(v);
        }

        Vertex[] toArray() {
            return vertices.toArray(new Vertex[0]);
        }
    }

    // Method to find multiple distinct paths
    public List<Vertex[]> findDistinctPaths(Vertex start, Vertex end, int numberOfPaths) {
        List<Vertex[]> distinctPaths = new ArrayList<>();
        findPathsDFS(start, end, new Path(), distinctPaths, numberOfPaths);
        return distinctPaths;
    }

    private void findPathsDFS(Vertex current, Vertex end, Path currentPath, List<Vertex[]> paths, int numberOfPaths) {
        // Add the current vertex to the path
        currentPath.add(current);

        if (current.equals(end)) {
            // Found a path
            paths.add(currentPath.toArray());
            if (paths.size() == numberOfPaths) {
                // Found the required number of paths
                return;
            }
        } else {
            // Explore neighbors
            for (int[] direction : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) { // Directions: down, right, up, left
                int newX = current.getX() + direction[0];
                int newY = current.getY() + direction[1];
                Vertex neighbor = new Vertex(newX, newY);

                if (isValidStep(neighbor) && !currentPath.contains(neighbor)) {
                    // Continue the path with the neighbor
                    findPathsDFS(neighbor, end, new Path(currentPath), paths, numberOfPaths);
                    if (paths.size() == numberOfPaths) {
                        // If the number of required paths has been found, stop searching
                        return;
                    }
                }
            }
        }
        // Remove the current vertex from the path when backtracking
        currentPath.vertices.remove(currentPath.vertices.size() - 1);
        currentPath.visited.remove(current);
    }

    // Check if the step is within bounds and not a wall
    private boolean isValidStep(Vertex v) {
        return v.getX() >= 0 && v.getX() < maze_matrix[0].length && v.getY() >= 0 && v.getY() < maze_matrix.length && maze_matrix[v.getY()][v.getX()] == 0;
    }


    public void colorMazeWithPath(Vertex[] path, int color){
        for (Vertex v: path){
            int x_loc = v.getX();
            int y_loc = v.getY();
            output_matrix[y_loc][x_loc] = color;
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

    private class NodeState {
        Vertex vertex;
        NodeState parent;

        NodeState(Vertex vertex, NodeState parent) {
            this.vertex = vertex;
            this.parent = parent;
        }
    }
}




