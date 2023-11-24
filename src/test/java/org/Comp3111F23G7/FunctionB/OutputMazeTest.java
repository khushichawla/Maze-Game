package org.Comp3111F23G7.FunctionB;

import static org.junit.jupiter.api.Assertions.*;
import org.Comp3111F23G7.FunctionB.OutputMaze;
import org.Comp3111F23G7.IgnoreIOExceptionExtension;
import org.Comp3111F23G7.Vertex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(IgnoreIOExceptionExtension.class)
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
        int[][] testMatrix = {{1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
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
        File file = new File("src/main/java/org/Comp3111F23G7/FunctionB/paths.csv");
        file.createNewFile();
        outputMaze = new OutputMaze(testMatrix, 49, new Vertex(0, 12), new Vertex(29, 1)); // Using the length of your shortest path
    }

    @AfterEach
    void tearDown() throws IOException {
        outputMaze.closeFileWriter();
        Files.deleteIfExists(Paths.get(csvFilePath)); // Clean up the CSV file after each test
        Files.deleteIfExists(Paths.get("maze_output.txt"));
    }

    @Test
    void testColorMazeWithPath() throws IOException {
        outputMaze.colorMazeWithPath(shortestPath); //target function
        outputMaze.colorMazeWithPath(alternatePath1); //target function
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                assertTrue(line.startsWith("SP, ") || line.startsWith("OP, "));
            }
        }

        String invalidFilePath = "./invalid/path.csv"; // Example of a likely invalid path
        try (BufferedReader reader = new BufferedReader(new FileReader(invalidFilePath))){};

    }

    @Test
    void testColorMazeWithMultiPath(){
        List<Vertex[]> paths = new ArrayList<>(Arrays.asList(shortestPath, alternatePath1, alternatePath2));
        outputMaze.colorMazeWithMultiPath(paths, shortestPath.length); //target function
    }

    @Test
    void testOutputTextMaze() throws IOException {
        outputMaze.outputTextMaze();
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("maze_output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        }
        String expected = "44444444444444444444444444444444\n" +
                "49909999999099999909999999999994\n" +
                "40990009009000900990090009000034\n" +
                "40099009909990900099099099909994\n" +
                "49909900900090999909909090909094\n" +
                "40900900999090090000000090909094\n" +
                "40900999009090090900990990009094\n" +
                "49990009909099990999090000999094\n" +
                "49099000999009000009099900900994\n" +
                "49009990000099090909900009909904\n" +
                "49000099909990000900999099099094\n" +
                "49009000909000999990090000090094\n" +
                "49999090999090000090099999099994\n" +
                "47009990000099999099090000009004\n" +
                "49900099909990900009000999909904\n" +
                "40909000000090909999909900900994\n" +
                "40909999090000999000009099999094\n" +
                "49999000099990900099909090000094\n" +
                "49009990900090009090999099099994\n" +
                "49900099009090099000000009090904\n" +
                "40990009909099990009999909090994\n" +
                "49099990909000000099090009090094\n" +
                "49000090909990999990090999090994\n" +
                "49999990900099909099090900000904\n" +
                "49000000099000909000090909999994\n" +
                "49990999909900900999090900090094\n" +
                "40090090900990000909090990090004\n" +
                "49099990909900990909090099099994\n" +
                "49000000909000099909090009000094\n" +
                "49999990900009000009900999900994\n" +
                "49000099999999999990999900999904\n" +
                "44444444444444444444444444444444\n";
        assertEquals(expected.trim(), fileContent.toString().trim());
    }
}


