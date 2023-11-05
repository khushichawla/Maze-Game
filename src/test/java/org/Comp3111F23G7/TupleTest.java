package org.Comp3111F23G7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    Tuple someTuple;
    @BeforeEach
    void beforeEach(){
        someTuple = new Tuple(1, 2);
    }

    @Test
    void changeData() {
        someTuple.ChangeData(3,4);
        assertEquals(someTuple.x, 3);
        assertEquals(someTuple.y, 4);
    }

    @Test
    void getX() {
    }

    @Test
    void getY() {
    }

    @Test
    void getXf() {
    }

    @Test
    void getYf() {
    }
}