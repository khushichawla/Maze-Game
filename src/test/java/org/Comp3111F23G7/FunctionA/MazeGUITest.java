package org.Comp3111F23G7.FunctionA;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.*;

public class MazeGUITest {
    @Test
    void loadMazeFromFile_InvalidFilePath_ReturnsNull() {
        int[][] actualMaze = MazeGUI.loadMazeFromFile("invalid_file.txt");
        assertNull(actualMaze);
    }

    @Test
    void loadMazeFromFile_ValidFilePath_ReturnsMazeArray() {
        try {
            // Create a temporary file and write maze data
            String mazeData = "111\n101\n111";
            Path tempFilePath = Files.createTempFile("temp_maze", ".txt");
            Files.writeString(tempFilePath, mazeData, StandardOpenOption.WRITE);

            // Load the maze from the temporary file
            int[][] actualMaze = MazeGUI.loadMazeFromFile(tempFilePath.toString());

            // Assert that the maze is not null
            assertNotNull(actualMaze);

            // Assert that the maze has valid dimensions
            int rows = actualMaze.length;
            assertTrue(rows > 0);
            int cols = actualMaze[0].length;
            assertTrue(cols > 0);

            // Clean up the temporary file
            Files.deleteIfExists(tempFilePath);
        } catch (IOException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void createAndShowMazeGUI_MazeWithGreenColor_PanelsHaveDefaultBackgroundColor() {
        // Specify the path to the text file containing the maze
        String mazeFilePath = "mazeTest3.txt";

        int[][] maze = loadMazeFromFile(mazeFilePath);

        MazeGUI.createAndShowMazeGUI(maze);

        // Delay to allow the GUI to be fully rendered
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the JFrame from the maze GUI
        JFrame frame = getMainFrame();

        // Assert that all panels have the color GREEN
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                assertEquals(Color.GREEN, ((JPanel) component).getBackground());
            }
        }
    }
    @Test
    void createAndShowMazeGUI_MazeWithInvalidColor_PanelsHaveDefaultBackgroundColor() {
        // Specify the path to the text file containing the maze
        String mazeFilePath = "mazeTest1.txt";

        int[][] maze = loadMazeFromFile(mazeFilePath);

        MazeGUI.createAndShowMazeGUI(maze);

        // Delay to allow the GUI to be fully rendered
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the JFrame from the maze GUI
        JFrame frame = getMainFrame();

        // Assert that all panels do not have color RED as the file is all GRAY
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                assertNotEquals(Color.RED, ((JPanel) component).getBackground());
            }
        }
    }

    @Test
    void createAndShowMazeGUI_MazeWithWhiteColor_PanelsHaveDefaultBackgroundColor() {
        // Specify the path to the text file containing the maze
        String mazeFilePath = "mazeTest2.txt";

        int[][] maze = loadMazeFromFile(mazeFilePath);

        MazeGUI.createAndShowMazeGUI(maze);

        // Delay to allow the GUI to be fully rendered
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the JFrame from the maze GUI
        JFrame frame = getMainFrame();

        // Assert that all panels do not have the default background color as it should be all WHITE
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                assertNotEquals(Color.GRAY, ((JPanel) component).getBackground());
            }
        }
    }

    @Test
    void createAndShowMazeGUI_MazeWithRedColor_PanelsHaveDefaultBackgroundColor() {
        // Specify the path to the text file containing the maze
        String mazeFilePath = "mazeTest4.txt";

        int[][] maze = loadMazeFromFile(mazeFilePath);

        MazeGUI.createAndShowMazeGUI(maze);

        // Delay to allow the GUI to be fully rendered
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the JFrame from the maze GUI
        JFrame frame = getMainFrame();

        // Assert that all panels do not have BLACK color as it should be all RED
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                assertNotEquals(Color.BLACK, ((JPanel) component).getBackground());
            }
        }
    }

    @Test
    void createAndShowMazeGUI_MazeWithBlackColor_PanelsHaveDefaultBackgroundColor() {
        // Specify the path to the text file containing the maze
        String mazeFilePath = "mazeTest5.txt";

        int[][] maze = loadMazeFromFile(mazeFilePath);

        MazeGUI.createAndShowMazeGUI(maze);

        // Delay to allow the GUI to be fully rendered
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the JFrame from the maze GUI
        JFrame frame = getMainFrame();

        // Assert that all panels do not have WHITE color as it should be all BLACK
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                assertNotEquals(Color.WHITE, ((JPanel) component).getBackground());
            }
        }
    }

    private int[][] loadMazeFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            int[][] maze = null;

            while ((line = reader.readLine()) != null) {
                if (maze == null) {
                    int rows = line.length();
                    int cols = line.length();
                    maze = new int[rows][cols];
                }

                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    maze[row][col] = Character.getNumericValue(c);
                }

                row++;
            }

            return maze;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private JFrame getMainFrame() {
        for (Window window : Window.getWindows()) {
            if (window instanceof JFrame) {
                return (JFrame) window;
            }
        }
        throw new IllegalStateException("Main frame not found");
    }

//    @Test
//    void main_ValidMazeFile_CreatesAndShowsGUI() {
//        // Specify the path to the valid maze text file
//        String mazeFilePath = "mazeTest.txt";
//
//        // Invoke the main method
//        MazeGUI mazeGUI = new MazeGUI();
//        MazeGUI.main(new String[]{mazeFilePath});
//
//        // Assert that the GUI is visible
//        assertTrue(mazeGUI.isGUIVisible());
//    }
}