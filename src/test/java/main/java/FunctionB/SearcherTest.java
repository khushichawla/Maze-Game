package main.java.FunctionB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {

    Searcher searcher;

    @BeforeEach
    void beforeEach(){
        searcher = new Searcher(SearchMethod.BFS);
    }

    @Test
    void getSearchMethod() {
        assertEquals(searcher.getSearchMethod(), SearchMethod.BFS);
    }

    @Test
    void setSearchMethod() {
        searcher.setSearchMethod(SearchMethod.DFS);
        assertEquals(searcher.getSearchMethod(), SearchMethod.DFS);
    }
}