package org.Comp3111F23G7.FunctionC;

import org.Comp3111F23G7.FunctionA.MazeGenerator;
import org.Comp3111F23G7.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
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
        Vertex end = new Vertex(0,0);
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
        String userInput = "d\ns\ns\nd\nd\nd";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        MazeGenerator mazeNew = new MazeGenerator(30,30);
        Game.playGame(maze, jerry, tom, mazeNew);
        assertEquals(3, jerry[0]);
        assertEquals(4, jerry[1]);
        assertEquals(3, tom[0]);
        assertEquals(3, tom[1]);
    }

    @Test
    public void testCalculateNextMove() {
        int[] currentPos = {2, 2};
        int[] expectedMoveUp = {1, 2};
        assertArrayEquals(expectedMoveUp, Game.calculateNextMove(currentPos, "w"));
        int[] expectedMoveLeft = {2, 1};
        assertArrayEquals(expectedMoveLeft, Game.calculateNextMove(currentPos, "a"));
        int[] expectedMoveDown = {3, 2};
        assertArrayEquals(expectedMoveDown, Game.calculateNextMove(currentPos, "s"));
        int[] expectedMoveRight = {2, 3};
        assertArrayEquals(expectedMoveRight, Game.calculateNextMove(currentPos, "d"));
        int[] expectedStay = {2, 2};
        assertArrayEquals(expectedStay, Game.calculateNextMove(currentPos, "x"));
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
        int[] validMove = {1, 1};
        assertTrue(Game.isValidMove(validMove, maze));
        int[] outOfBoundsMove = {2, 2};
        assertFalse(Game.isValidMove(outOfBoundsMove, maze));
        int[] wallMove = {0, 0};
        assertFalse(Game.isValidMove(wallMove, maze));
    }
}