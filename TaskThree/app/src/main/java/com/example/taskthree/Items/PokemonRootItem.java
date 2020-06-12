package com.example.taskthree.Items;

import java.util.List;

public class PokemonRootItem {
    public int count;
    public String next;
    public String previous;
    public List<ResultsOfItem> results;

    public int getCount() {
        return count;
    }

    public void setCount( int count ) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext( String next ) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious( String previous ) {
        this.previous = previous;
    }

    public List<ResultsOfItem> getResults() {
        return results;
    }

    public void setResults( List<ResultsOfItem> results ) {
        this.results = results;
    }
}
