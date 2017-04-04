package com.example.mike.mvpsample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

/**
 * Created by MWidlok on 04.04.2017.
 */

public class NewNoteDialog extends DialogFragment
{

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_new_note, null))
		       .setPositiveButton("Hinzufügen", new DialogInterface.OnClickListener() {
			       @Override
			       public void onClick(DialogInterface dialogInterface, int i) {
				       Log.i("info", "now call the presenter to add the note");
			       }
		       })
		       .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
			       @Override
			       public void onClick(DialogInterface dialogInterface, int i) {
				       Log.i("info", "now close this dialog");
			       }
		       });

		return builder.create();
	}
}