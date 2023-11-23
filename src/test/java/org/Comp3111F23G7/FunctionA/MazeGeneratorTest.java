package org.Comp3111F23G7.FunctionA;

import org.Comp3111F23G7.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MazeGeneratorTest {
    private MazeGenerator mazeGenerator;

    @Test
    public void testGenerateMaze() {
        mazeGenerator = new MazeGenerator(30, 30);
        mazeGenerator.generateMaze();
        char[][] maze = getMaze();

        // Assert that the maze is generated correctly
        Assertions.assertNotNull(maze);
        Assertions.assertEquals(30, maze.length);
        Assertions.assertEquals(30, maze[0].length);

        // Assert that the maze contains valid characters
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                Assertions.assertTrue(maze[i][j] == '0' || maze[i][j] == '1'
                        || maze[i][j] == '2' || maze[i][j] == '3' || maze[i][j] == '4');
            }
        }
    }

    @Test
    public void testSaveMazeToFile() throws IOException {
        String filename = "maze.txt";
        mazeGenerator = new MazeGenerator(30, 30);
        mazeGenerator.generateMaze();
        mazeGenerator.saveMazeToFile(filename);

        // Assert that the maze file is created
        Path filePath = Paths.get(filename);
        Assertions.assertTrue(Files.exists(filePath));

        // Clean up the generated maze file
        Files.deleteIfExists(filePath);
    }

    private char[][] getMaze() {
        try {
            // Access the private maze field using reflection
            java.lang.reflect.Field mazeField = MazeGenerator.class.getDeclaredField("maze");
            mazeField.setAccessible(true);
            return (char[][]) mazeField.get(mazeGenerator);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testGetPointStart() {
        MazeGenerator mazeGenerator = new MazeGenerator(30, 30);
        mazeGenerator.generateMaze();
        // Call the getPointStart() function
        Vertex pointStart = mazeGenerator.getPointStart();

        // Verify that the x and y coordinates are within the maze boundaries
        Assertions.assertTrue(pointStart.getX() >= 0 && pointStart.getX() < 30);
        Assertions.assertTrue(pointStart.getY() >= 0 && pointStart.getY() < 30);
    }
    @Test
    public void testGetPointEnd() {
        MazeGenerator mazeGenerator = new MazeGenerator(30, 30);
        mazeGenerator.generateMaze();
        // Call the getPointEnd() function
        Vertex pointEnd = mazeGenerator.getPointEnd();

        // Verify that the x and y coordinates are within the maze boundaries
        Assertions.assertTrue(pointEnd.getX() >= 0 && pointEnd.getX() <= 30);
        Assertions.assertTrue(pointEnd.getY() >= 0 && pointEnd.getY() <= 30);
    }
}