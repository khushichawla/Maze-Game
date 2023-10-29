package main.java.FunctionB;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Searcher {

    private SearchMethod searchMethod;

    public Searcher(SearchMethod searchMethod){
        this.searchMethod = searchMethod;
    }


//    List<Vertex> getShortestPath(){
//
//    }
}
