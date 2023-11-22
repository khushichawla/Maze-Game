package org.Comp3111F23G7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void testPrintMaze() {
        int[][] maze = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        Game.printMaze(maze);
        String expectedOutput = "1 2 3 \n4 5 6 \n7 8 9 \n";
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    public void testUpdateMaze() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int[] jerry = {1, 1};
        int[] oldJerry = {-1, -1};
        int[] tom = {2, 2};
        int[] oldTom = {-1, -1};

        int[][] expectedMaze = {
                {0, 0, 0},
                {0, 2, 0},
                {0, 0, 3}
        };

        Game.updateMaze(maze, jerry, oldJerry, tom, oldTom);

        Assertions.assertArrayEquals(expectedMaze, maze);
    }
    @Test
    public void testPlayGame() {
        // Define the maze and initial positions
        int[][] maze = {
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int[] jerry = {1, 0};  // Jerry starts at (1, 0)
        int[] tom = {3, 4};    // Tom starts at (3, 4)

        // Define the user input for Jerry's moves
        String userInput = "d\ns\ns\nd\nd\nd";

        // Redirect System.in to provide the predefined user input
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        // Call the playGame function
        Game.playGame(maze, jerry, tom);

        // Verify the expected outcome
        assertEquals(3, jerry[0]);
        assertEquals(4, jerry[1]);
        assertEquals(3, tom[0]);
        assertEquals(3, tom[1]);
    }

    @Test
    public void testCalculateNextMove() {
        int[] currentPos = {2, 2};

        // Test moving up
        int[] expectedMoveUp = {1, 2};
        assertArrayEquals(expectedMoveUp, Game.calculateNextMove(currentPos, "w"));

        // Test moving left
        int[] expectedMoveLeft = {2, 1};
        assertArrayEquals(expectedMoveLeft, Game.calculateNextMove(currentPos, "a"));

        // Test moving down
        int[] expectedMoveDown = {3, 2};
        assertArrayEquals(expectedMoveDown, Game.calculateNextMove(currentPos, "s"));

        // Test moving right
        int[] expectedMoveRight = {2, 3};
        assertArrayEquals(expectedMoveRight, Game.calculateNextMove(currentPos, "d"));

        // Test staying in the same position
        int[] expectedStay = {2, 2};
        assertArrayEquals(expectedStay, Game.calculateNextMove(currentPos, "x"));

        // Test exit move
        int[] expectedExit = {-1, -1};
        assertArrayEquals(expectedExit, Game.calculateNextMove(currentPos, "q"));
    }
    @Test
    public void testIsValidMove() {
        int[][] maze = {
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        };

        // Valid move
        int[] validMove = {1, 1};
        assertTrue(Game.isValidMove(validMove, maze));

        // Corrected invalid move: out of maze bounds
        int[] outOfBoundsMove = {2, 2};
        assertFalse(Game.isValidMove(outOfBoundsMove, maze));

        // Invalid move: hitting a wall
        int[] wallMove = {0, 0};
        assertFalse(Game.isValidMove(wallMove, maze));
    }
}