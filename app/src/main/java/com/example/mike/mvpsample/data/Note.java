package com.example.mike.mvpsample.data;

/**
 * Created by Mike on 19.03.2017.
 */

public class Note {

    private int id;
    private String title;
    private String description;


    public Note(int id, String title, String description)
    {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

}
