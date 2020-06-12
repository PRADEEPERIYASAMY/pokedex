package com.example.taskthree.Count;

import java.util.List;

public class PokemonsCount {
    public int count;
    public String next;
    public String previous;
    public List<ResultsItem> results;

    public PokemonsCount() {

    }

    public PokemonsCount( int count, String next, String previous, List<ResultsItem> results ) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

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

    public List<ResultsItem> getResults() {
        return results;
    }

    public void setResults( List<ResultsItem> results ) {
        this.results = results;
    }
}
