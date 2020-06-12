package com.example.taskthree.Regions;

import java.util.List;

public class RootRegions {
    public int count ;
    public String next ;
    public String previous ;
    public List<ResultsRegion> results ;

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

    public List<ResultsRegion> getResults() {
        return results;
    }

    public void setResults( List<ResultsRegion> results ) {
        this.results = results;
    }
}
