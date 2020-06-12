package com.example.taskthree.Location;

import java.util.List;

public class RootLocation {
    public int count;
    public String next;
    public String previous;
    public List<ResultLocation> results;

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

    public List<ResultLocation> getResults() {
        return results;
    }

    public void setResults( List<ResultLocation> results ) {
        this.results = results;
    }
}
