package com.example.mike.mvpsample.classes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mike.mvpsample.R;
import com.example.mike.mvpsample.data.Note;

import java.util.List;

/**
 * Created by MWidlok on 07.04.2017.
 */

public class RvNotesListAdapter extends RecyclerView.Adapter<RvNotesListAdapter.ViewHolder> {

	private List<Note> notesDataset;

	// Referenz auf den View eines jeden Items in der Liste.
	public static class ViewHolder extends RecyclerView.ViewHolder
	{

		public TextView tvNote;  // ein simpler TextView für ein Note Item.
		public ViewHolder(TextView tv)
		{
			super(tv);
			tvNote = tv;
		}
	}

	// Konstruktor der initial die Daten erhält..

	public RvNotesListAdapter(List<Note> notes)
	{
		notesDataset = notes;
	}

	@Override
	public RvNotesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		TextView tvNote = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_noteitem, parent, false);
		ViewHolder vh = new ViewHolder(tvNote);
		return vh;
	}

	@Override
	public void onBindViewHolder(RvNotesListAdapter.ViewHolder holder, int position) {
		holder.tvNote.setText(notesDataset.get(position).getTitle());
	}

	@Override
	public int getItemCount() {
		return notesDataset.size();
	}
}
