/**
 * The {@code Searcher} class in the package {@code org.Comp3111F23G7.FunctionB}
 * is designed to perform various search operations within a maze represented by a 2D integer array.
 * It supports breadth-first search (BFS), Dijkstra's algorithm for shortest path finding,
 * and a method to find multiple distinct paths between two vertices in the maze.
 * The maze is represented as a 2D integer array where 0 indicates an open path and 1 indicates a wall.
 *
 * @author Virginia Tsang
 * @version 1.0
 */

package org.Comp3111F23G7.FunctionB;
import org.Comp3111F23G7.Vertex;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class Searcher {
    private int[][] maze_matrix;
    /**
     * Constructs a new {@code Searcher} with the specified maze.
     *
     * @param maze_matrix The 2D integer array representing the maze.
     */
    public Searcher(int[][] maze_matrix) {
        this.maze_matrix = maze_matrix;

    }
    /**
     * Performs a breadth-first search (BFS) from the start vertex to the end vertex in the maze.
     *
     * @param start The starting vertex for the BFS.
     * @param end The end vertex for the BFS.
     * @return An array of {@code Vertex} objects representing the path from start to end, or an empty array if no path is found.
     */
    public Vertex[] bfs(Vertex start, Vertex end) {
        this.maze_matrix[start.getY()][start.getX()] =0;
        this.maze_matrix[end.getY()][end.getX()] =0;

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
    /**
     * Performs Dijkstra's algorithm to find the shortest path from the start vertex to the end vertex in the maze.
     *
     * @param start The starting vertex.
     * @param end The end vertex.
     * @return An array of {@code Vertex} objects representing the shortest path from start to end, or an empty array if no path is found.
     */
    public Vertex[] dijkstra(Vertex start, Vertex end) {
        this.maze_matrix[start.getY()][start.getX()] =0;
        this.maze_matrix[end.getY()][end.getX()] =0;

        for(int j=0;j< maze_matrix.length;++j){
            for (int i=0; i<maze_matrix.length;++i){
                System.out.print(maze_matrix[j][i]);}
            System.out.print("\n");
        }


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

    /**
     * Finds a specified number of distinct paths from the start vertex to the end vertex in the maze using depth-first search (DFS).
     *
     * @param start The starting vertex.
     * @param end The end vertex.
     * @param numberOfPaths The number of distinct paths to find.
     * @return A list of {@code Vertex[]} where each array represents a distinct path from start to end.
     */
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

    /**
     * A private helper method to check if a given vertex represents a valid step in the maze.
     *
     * @param v The vertex to check.
     * @return {@code true} if the vertex is within bounds and not a wall, otherwise {@code false}.
     */
    private boolean isValidStep(Vertex v) {
        return v.getX() >= 0 && v.getX() < maze_matrix[0].length && v.getY() >= 0 && v.getY() < maze_matrix.length && maze_matrix[v.getY()][v.getX()] == 0;
    }

    private class NodeState {
        Vertex vertex;
        NodeState parent;

        NodeState(Vertex vertex, NodeState parent) {
            this.vertex = vertex;
            this.parent = parent;
        }
    }
    /**
     * Gets the current maze matrix.
     *
     * @return The 2D integer array representing the maze.
     */
    public int[][] getMaze_matrix(){
        return maze_matrix;
    }
}




