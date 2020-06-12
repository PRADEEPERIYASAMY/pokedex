package com.example.taskthree.Models;

import java.util.List;

public class MovesItem {
    public Move move;
    public List<Version_group_detailsItem> version_group_details;

    public Move getMove() {
        return move;
    }

    public void setMove( Move move ) {
        this.move = move;
    }

    public List<Version_group_detailsItem> getVersion_group_details() {
        return version_group_details;
    }

    public void setVersion_group_details( List<Version_group_detailsItem> version_group_details ) {
        this.version_group_details = version_group_details;
    }
}
