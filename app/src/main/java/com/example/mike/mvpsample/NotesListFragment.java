package com.example.mike.mvpsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mike.mvpsample.classes.NotesContract;
import com.example.mike.mvpsample.classes.NotesPresenter;
import com.example.mike.mvpsample.classes.adapters.RvNotesListAdapter;
import com.example.mike.mvpsample.data.Note;
import java.util.ArrayList;
import java.util.List;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotesListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesListFragment extends Fragment implements NotesContract.View {

    private OnFragmentInteractionListener mListener;
    private NotesContract.Presenter mPresenter;

    private RecyclerView rvNotesList;
    private RvNotesListAdapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    List<Note> dataList = new ArrayList<Note>();

    SQLiteDatabase notesDb = null;

    public NotesListFragment() {
        // Required empty public constructor
    }

    public static NotesListFragment newInstance(String param1, String param2) {
        NotesListFragment fragment = new NotesListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NotesPresenter();

        notesDb = getActivity().openOrCreateDatabase(MainActivity.DB_NAME, MODE_PRIVATE, null);

        // todo now read all the content from db and print it.
        // therefore we have to define some information..

        String[] projection = { MainActivity.COLUMN_NAME_HEAD, MainActivity.COLUMN_NAME_CONTENT};
        String sortOrder = MainActivity.COLUMN_NAME_HEAD + " DESC";

        Cursor cursor = notesDb.query(
                MainActivity.NOTES_TABLE,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);

        try
        {
            while(cursor.moveToNext())
            {

                // Attention: Id of note is always 0! This has to be changed before release!
                long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(MainActivity.COLUMN_NAME_HEAD));
                String title = cursor.getString(cursor.getColumnIndex(MainActivity.COLUMN_NAME_HEAD));
                String description = cursor.getString(cursor.getColumnIndex(MainActivity.COLUMN_NAME_CONTENT));
                Note note = new Note((int) itemId,title,description);
                Log.i("mvpInfo","Current head is " + note.getTitle() +". Writing it into dataList...");
                dataList.add(note);
            }
            cursor.close();
        }
        catch(Exception exc)
        {
            Log.e("mvpInfo","Db Read failed. Reason: " + exc.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvNotesList = (RecyclerView) view.findViewById(R.id.rvNotesList);
        rvNotesList.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(getActivity());
        rvNotesList.setLayoutManager(rvLayoutManager);
        rvAdapter = new RvNotesListAdapter(dataList);
        rvNotesList.setAdapter(rvAdapter);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        final NewNoteDialog newNoteDialog = new NewNoteDialog();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNoteDialog.setMyDialogListener(new NewNoteDialog.MyDialogListener() {
                    @Override
                    public void userSelectedValue(Note note) {
                        dataList.add(note);
                        Log.i("mvpInfo","item count from adapter is " + rvAdapter.getItemCount());
                        rvAdapter.notifyDataSetChanged();

                        try
                        {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(MainActivity.COLUMN_NAME_HEAD, note.getTitle());
                            contentValues.put(MainActivity.COLUMN_NAME_CONTENT, note.getDescription());
                            long newRowId = notesDb.insert(MainActivity.NOTES_TABLE, null, contentValues);
                            Log.i("mvpInfo","Row was successfully created. Id is " + newRowId);
                        }
                        catch(Exception exc) {
                            Log.i("mvpInfo","An error occured during creating the row. Reason: " + exc.getMessage());
                        }
                    }

                    @Override
                    public void userCanceled() {
                    }
                });
                newNoteDialog.show(getActivity().getSupportFragmentManager(), "test");
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void updateNotesListView() {

    }

    @Override
    public void showDeleteCompleted() {

    }

    @Override
    public void showCreateCompleted() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
