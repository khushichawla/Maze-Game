package main.java;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SquarePanelTest {

    @Test
    void changeColor() {
        SquarePanel squarePanel = new SquarePanel(Color.BLUE);
        squarePanel.ChangeColor(Color.BLACK);
        assertEquals(squarePanel.getBackground(), Color.BLACK);
    }
}