import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.Vertex;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {

    Vertex someVertex;
    @BeforeEach
    void beforeEach(){
        someVertex = new Vertex(1, 2);
    }
    @Test
    void getX() {
        assertEquals(someVertex.getX(), 1);
    }

    @Test
    void getY() {
        assertEquals(someVertex.getY(), 2);
    }

    @Test
    void setX() {
        someVertex.setX(3);
        assertEquals(someVertex.getX(), 3);
    }

    @Test
    void setY() {
        someVertex.setY(4);
        assertEquals(someVertex.getY(), 4);
    }
}