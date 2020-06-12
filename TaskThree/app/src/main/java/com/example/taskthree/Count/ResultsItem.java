package com.example.taskthree.Count;

public class ResultsItem {
    public String name;
    public String url;

    public ResultsItem() {
    }

    public ResultsItem( String name, String url ) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }
}
