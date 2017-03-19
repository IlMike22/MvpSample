package com.example.mike.mvpsample.classes;

import android.util.Log;

/**
 * Created by Mike on 19.03.2017.
 */

public class NotesPresenter implements NotesContract.Presenter {


    @Override
    public void addNewNote() {

        Log.i("PRESENTER","Hello from Presenter");

    }

    @Override
    public void editNote() {

    }

    @Override
    public void deleteNote() {

    }
}
