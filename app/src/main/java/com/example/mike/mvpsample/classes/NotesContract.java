package com.example.mike.mvpsample.classes;

/**
 * Created by Mike on 19.03.2017.
 */

public interface NotesContract {

    public interface Presenter
    {
        // presenter which has all the (business) logic in it

        void addNewNote();
        void editNote();
        void deleteNote();


    }

    public interface View
    {
        // view which has no logic but only changes in ui

        void setLoadingIndicator(boolean active);
        void updateNotesListView();
        void showDeleteCompleted();
        void showCreateCompleted();


    }
}
