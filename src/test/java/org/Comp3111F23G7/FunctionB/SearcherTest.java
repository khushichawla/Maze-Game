package org.Comp3111F23G7.FunctionB;

import org.Comp3111F23G7.FunctionB.Searcher;
import org.Comp3111F23G7.Vertex;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {

    private final int[][] test_maze1 = {{1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
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
    private final Vertex start1 = new Vertex(0, 12); // Entry-Point
    private final Vertex end1 = new Vertex(29, 1); // Exit-Point

    @Test
    void testSearcherConstructor(){
        Searcher searcher = new Searcher(test_maze1); //target function
        assertEquals(searcher.getMaze_matrix(), test_maze1);
    }

    @Test
    void testSearcherGetter(){
        Searcher searcher = new Searcher(test_maze1);
        int[][] m = searcher.getMaze_matrix();//target function
        assertEquals(m, test_maze1);
    }

    @Test
    void testBfsWithProvidedMaze() {
        Searcher searcher = new Searcher(test_maze1);
        Vertex[] path = searcher.bfs(start1, end1); //target function
        assertNotEquals(0, path.length, "Path should not be empty");
        assertEquals(start1, path[0], "Path should start at the Entry-Point");
        assertEquals(end1, path[path.length - 1], "Path should end at the Exit-Point");
    }

    @Test
    void testDijkstraWithProvidedMaze() {
        Searcher searcher = new Searcher(test_maze1);
        Vertex[] path = searcher.dijkstra(start1, end1); //target function
        assertNotEquals(0, path.length, "Path should not be empty");
        assertEquals(start1, path[0], "Path should start at the Entry-Point");
        assertEquals(end1, path[path.length - 1], "Path should end at the Exit-Point");
    }

    @Test
    void testFindDistinctPathsWithProvidedMaze() {
        Searcher searcher = new Searcher(test_maze1);
        int numberOfPaths = 5;
        List<Vertex[]> paths = searcher.findDistinctPaths(start1, end1, numberOfPaths); //target function
        for (Vertex[] path : paths) {
            assertNotEquals(0, path.length, "No path should be empty");
            assertEquals(start1, path[0], "Each path should start at the Entry-Point");
            assertEquals(end1, path[path.length - 1], "Each path should end at the Exit-Point");
        }

    }

}