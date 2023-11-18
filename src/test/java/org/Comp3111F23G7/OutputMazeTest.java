package org.Comp3111F23G7;

import static org.junit.jupiter.api.Assertions.*;
import org.Comp3111F23G7.FunctionB.OutputMaze;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class OutputMazeTest {

    private OutputMaze outputMaze;
    private int[][] testMatrix;
    private final String csvFilePath = "src/main/java/org/Comp3111F23G7/FunctionB/paths.csv";

    private Vertex[] shortestPath = {
            new Vertex(0, 12), new Vertex(1, 12), new Vertex(2, 12), new Vertex(2, 13), new Vertex(3, 13),
            new Vertex(4, 13), new Vertex(4, 14), new Vertex(5, 14), new Vertex(6, 14), new Vertex(7, 14),
            new Vertex(8, 14), new Vertex(8, 13), new Vertex(8, 12), new Vertex(9, 12), new Vertex(10, 12),
            new Vertex(10, 11), new Vertex(10, 10), new Vertex(11, 10), new Vertex(12, 10), new Vertex(12, 9),
            new Vertex(13, 9), new Vertex(14, 9), new Vertex(15, 9), new Vertex(15, 8), new Vertex(15, 7),
            new Vertex(15, 6), new Vertex(15, 5), new Vertex(15, 4), new Vertex(16, 4), new Vertex(17, 4),
            new Vertex(18, 4), new Vertex(19, 4), new Vertex(20, 4), new Vertex(21, 4), new Vertex(21, 5),
            new Vertex(21, 6), new Vertex(22, 6), new Vertex(23, 6), new Vertex(24, 6), new Vertex(24, 5),
            new Vertex(25, 5), new Vertex(26, 5), new Vertex(26, 4), new Vertex(26, 3), new Vertex(26, 2),
            new Vertex(26, 1), new Vertex(27, 1), new Vertex(28, 1), new Vertex(29, 1)
    };

    private Vertex[] alternatePath1 = {
            new Vertex(0, 12), new Vertex(1, 12), new Vertex(2, 12), new Vertex(2, 13), new Vertex(3, 13),
            new Vertex(4, 13), new Vertex(4, 14), new Vertex(5, 14), new Vertex(6, 14), new Vertex(7, 14),
            new Vertex(8, 14), new Vertex(9, 14), new Vertex(9, 15), new Vertex(10, 15), new Vertex(11, 15),
            new Vertex(12, 15), new Vertex(12, 16), new Vertex(12, 17), new Vertex(12, 18), new Vertex(13, 18),
            new Vertex(13, 17), new Vertex(14, 17), new Vertex(14, 16), new Vertex(15, 16), new Vertex(16, 16),
            new Vertex(16, 15), new Vertex(17, 15), new Vertex(18, 15), new Vertex(19, 15), new Vertex(20, 15),
            new Vertex(20, 14), new Vertex(20, 13), new Vertex(21, 13), new Vertex(21, 12), new Vertex(22, 12),
            new Vertex(23, 12), new Vertex(24, 12), new Vertex(25, 12), new Vertex(25, 11), new Vertex(25, 10),
            new Vertex(24, 10), new Vertex(23, 10), new Vertex(22, 10), new Vertex(22, 9), new Vertex(22, 8),
            new Vertex(23, 8), new Vertex(23, 7), new Vertex(24, 7), new Vertex(24, 6), new Vertex(24, 5),
            new Vertex(25, 5), new Vertex(26, 5), new Vertex(26, 4), new Vertex(26, 3), new Vertex(26, 2),
            new Vertex(26, 1), new Vertex(27, 1), new Vertex(28, 1), new Vertex(29, 1)
    };

    private Vertex[] alternatePath2 = {
            new Vertex(0, 12), new Vertex(1, 12), new Vertex(2, 12), new Vertex(2, 13), new Vertex(3, 13),
            new Vertex(4, 13), new Vertex(4, 14), new Vertex(5, 14), new Vertex(6, 14), new Vertex(7, 14),
            new Vertex(8, 14), new Vertex(9, 14), new Vertex(9, 15), new Vertex(10, 15), new Vertex(11, 15),
            new Vertex(12, 15), new Vertex(12, 16), new Vertex(12, 17), new Vertex(12, 18), new Vertex(13, 18),
            new Vertex(13, 17), new Vertex(14, 17), new Vertex(14, 16), new Vertex(15, 16), new Vertex(16, 16),
            new Vertex(16, 15), new Vertex(17, 15), new Vertex(18, 15), new Vertex(19, 15), new Vertex(20, 15),
            new Vertex(20, 14), new Vertex(20, 13), new Vertex(21, 13), new Vertex(21, 12), new Vertex(22, 12),
            new Vertex(23, 12), new Vertex(24, 12), new Vertex(25, 12), new Vertex(25, 11), new Vertex(25, 10),
            new Vertex(24, 10), new Vertex(23, 10), new Vertex(22, 10), new Vertex(22, 9), new Vertex(22, 8),
            new Vertex(23, 8), new Vertex(23, 7), new Vertex(23, 6), new Vertex(24, 6), new Vertex(24, 5),
            new Vertex(25, 5), new Vertex(26, 5), new Vertex(26, 4), new Vertex(26, 3), new Vertex(26, 2),
            new Vertex(26, 1), new Vertex(27, 1), new Vertex(28, 1), new Vertex(29,1)
    };

    @BeforeEach
    void setUp() throws IOException {
        testMatrix = new int[30][30]; // Initialize with some test data
        outputMaze = new OutputMaze(testMatrix, 49); // Using the length of your shortest path
    }

    @AfterEach
    void tearDown() throws IOException {
        outputMaze.closeFileWriter();
        Files.deleteIfExists(Paths.get(csvFilePath)); // Clean up the CSV file after each test
    }

    @Test
    void testColorMazeWithPath() throws IOException {
        outputMaze.colorMazeWithPath(shortestPath);
        outputMaze.colorMazeWithPath(alternatePath1);
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                assertTrue(line.startsWith("SP, ") || line.startsWith("OP, "));
            }
        }
    }

    @Test
    void testColorMazeWithMultiPath() throws IOException {
        List<Vertex[]> paths = new ArrayList<>(Arrays.asList(shortestPath, alternatePath1, alternatePath2));
        outputMaze.colorMazeWithMultiPath(paths, shortestPath.length);
    }




}

