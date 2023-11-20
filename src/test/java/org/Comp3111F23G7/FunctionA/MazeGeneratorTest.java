package org.Comp3111F23G7.FunctionA;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
}