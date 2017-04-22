package com.example.mike.mvpsample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
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

import java.util.ArrayList;
import java.util.List;


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

    List<String> dataList = new ArrayList<String>();

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
                    public void userSelectedValue(String value) {
                        Log.i("info","We are in fragment and value of dialog is " + value);
                        dataList.add(value);
                        Log.i("info","item count from adapter is " + rvAdapter.getItemCount());
                        rvAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void userCanceled() {
                        Log.i("info","we are now in fragement. user has closed the dialog.");

                    }
                });
                newNoteDialog.show(getActivity().getSupportFragmentManager(), "test");
                Log.i("info","dialog successfully opened");

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
